package com.example.filippocenonfolo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class UpdateMatchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextGol1, editTextGol2;
    private TextView textViewTeam1, textViewTeam2;
    private Button buttonSetResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_match);

        Bundle data = getIntent().getExtras();
        final String squadra1 = data.getString("squadra1");
        final String squadra2 = data.getString("squadra2");

        editTextGol1 = (EditText) findViewById(R.id.editTextGol1);
        editTextGol2 = (EditText) findViewById(R.id.editTextGol2);
        textViewTeam1 = (TextView) findViewById(R.id.textViewTeam1);
        textViewTeam2 = (TextView) findViewById(R.id.textViewTeam2);

        textViewTeam1.setText(squadra1);
        textViewTeam2.setText(squadra2);

        buttonSetResult = (Button) findViewById(R.id.buttonSetResult);

        buttonSetResult.setOnClickListener(this);
    }

    public void setResult() {


        Bundle data = getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");
        final int idGirone = data.getInt("idGirone");
        final int idPartita = data.getInt("idPartita");

        final int gol1 = Integer.parseInt(editTextGol1.getText().toString());
        final int gol2 = Integer.parseInt(editTextGol2.getText().toString());


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_SET_RESULT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("error"))
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                params.put("id_girone", String.valueOf(idGirone).trim());
                params.put("id_partita", String.valueOf(idPartita).trim());
                params.put("gol_1", String.valueOf(gol1).trim());
                params.put("gol_2", String.valueOf(gol2).trim());
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {

        Bundle data = getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        if (view == buttonSetResult) {
            setResult();
            finish();
        }

    }
}
