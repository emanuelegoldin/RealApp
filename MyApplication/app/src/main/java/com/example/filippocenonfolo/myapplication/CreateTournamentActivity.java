package com.example.filippocenonfolo.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateTournamentActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTournamentName, editTextTournamentPlace;
    private Button buttonCreateNewTournament;
    private Switch switchFormat;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tournament);

        editTextTournamentName = (EditText) findViewById(R.id.editTextTournamentName);
        editTextTournamentPlace = (EditText) findViewById(R.id.editTextTournamentPlace);
        buttonCreateNewTournament = (Button) findViewById(R.id.buttonCreateNewTournament);
        switchFormat = (Switch) findViewById(R.id.switchFormat);
        progressDialog = new ProgressDialog(this);

        buttonCreateNewTournament.setOnClickListener(this);
    }

    private void createNewTournament() {

        final String name = editTextTournamentName.getText().toString().trim();
        final String place = editTextTournamentPlace.getText().toString().trim();
        boolean check = switchFormat.isChecked();
        int temp;

        final int creatore = SharedPrefManager.getInstance(getApplicationContext()).getUserId();

        if (check) {
            temp = 32;
        }
        else {
            temp = 16;
        }

        final int formato = temp;

        progressDialog.setMessage("Creating tournament...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_CREATE_TOURNAMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("error"))
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            else {
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                return;
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("creatore", String.valueOf(creatore));
                params.put("name", name);
                params.put("place", place);
                params.put("formato", String.valueOf(formato).trim());
                params.put("completo", String.valueOf(formato).trim());
                params.put("fase", String.valueOf(0).trim());
                return params;

            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }





    @Override
    public void onClick(View view) {
        if (view == buttonCreateNewTournament) {
            createNewTournament();
        }
    }
}
