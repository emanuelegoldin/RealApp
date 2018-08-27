package com.example.filippocenonfolo.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenerateGroupsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCreateGroups;
    private Button buttonGoToData;
    private TextView textViewCorrectInsertion;
    private ProgressDialog progressDialog;
    private ArrayList<String> teams;
    private HashMap<Integer, ArrayList<String>> groups;
    private ArrayList<Integer> order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_groups);

        Bundle data = getIntent().getExtras();
        final int format = data.getInt("formato");

        textViewCorrectInsertion = (TextView) findViewById(R.id.textViewCorrectInsertion);

        buttonGoToData = (Button) findViewById(R.id.buttonGoToData);
        buttonCreateGroups = (Button) findViewById(R.id.buttonCreateGroups);
        progressDialog = new ProgressDialog(this);
        teams = new ArrayList<>();
        groups = new HashMap<>();
        for (int i=0; i < (format/4); i++) {
            groups.put(i, new ArrayList<String>());
        }
        order = new ArrayList<>();
        populateTeams();
        buttonCreateGroups.setOnClickListener(this);
        buttonGoToData.setOnClickListener(this);
        buttonGoToData.setEnabled(false);



    }


    @Override
    public void onClick(View view) {
        if(view == buttonCreateGroups) {
            createGroups();
            buttonCreateGroups.setEnabled(false);
            buttonGoToData.setEnabled(true);
            createMatches();
        }
        if (view == buttonGoToData) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);
        }
    }

    private void populateTeams() {

        Bundle data = getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Downloading teams...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GET_TEAMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("teams");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                teams.add(o.getString("nome_squadra"));
                            }

                        } catch (JSONException e) {
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
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                return params;

            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void createGroups() {

        Bundle data = getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");
        final int format = data.getInt("formato");

        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < (format/4); k++) {

                groups.get(k).add(teams.get(cnt).trim());
                final int cnt1 = cnt;
                final int t = k;
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_INSERT_TEAM_GROUP,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getBoolean("error"))
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                    else {
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
                        params.put("id_torneo", String.valueOf(idTorneo).trim());
                        params.put("id_girone", String.valueOf(t).trim());
                        params.put("name", teams.get(cnt1).trim());
                        return params;

                    }
                };

                RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
                cnt++;

            }
        }
    }

    private void createMatches() {

        Bundle data = getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");
        final int format = data.getInt("formato");

        for (int i = 0; i < (format/4);  i++) {
            order.add(0);
            order.add(1);
            order.add(2);
            order.add(3);
            order.add(0);
            order.add(2);
            order.add(1);
            order.add(3);
            order.add(0);
            order.add(3);
            order.add(1);
            order.add(2);
        }


        for (int k = 0; k < (format/4); k++) {
            for (int j = 0; j < 6; j++) {

                final int t = k;
                final int s = j;
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_INSERT_MATCH_GROUP,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getBoolean("error")) {
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                        Toast.makeText(getApplicationContext(), t, Toast.LENGTH_LONG).show();
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
                        params.put("id_torneo", String.valueOf(idTorneo).trim());
                        params.put("id_girone", String.valueOf(t).trim());
                        params.put("id_partita", String.valueOf(s).trim());
                        params.put("nome_squadra_1", groups.get(t).get(order.remove(0)));
                        params.put("nome_squadra_2", groups.get(t).get(order.remove(0)));
                        return params;

                    }
                };

                RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            }
        }
    }
}
