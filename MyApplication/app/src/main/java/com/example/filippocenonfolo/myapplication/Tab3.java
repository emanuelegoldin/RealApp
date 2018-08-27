package com.example.filippocenonfolo.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
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
import java.util.List;
import java.util.Map;

public class Tab3 extends Fragment {

    private SwipeRefreshLayout swipeRefreshTab3;
    private RecyclerView recyclerViewOctave;
    private RecyclerView recyclerViewQuarter;
    private RecyclerView recyclerViewSemi;
    private RecyclerView recyclerViewFinal;
    private RecyclerView.Adapter adapterOctave;
    private RecyclerView.Adapter adapterQuarter;
    private RecyclerView.Adapter adapterSemi;
    private RecyclerView.Adapter adapterFinal;
    private ArrayList<String> teams;
    private ArrayList<Integer> order;
    private Button buttonCreateFinal;
    private TextView textViewOctave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container,false);
        teams = new ArrayList<>();
        order = new ArrayList<>();

        populateTeams();

        textViewOctave = (TextView) view.findViewById(R.id.textViewOctave);
        buttonCreateFinal = (Button) view.findViewById(R.id.buttonCreateFinal);
        buttonCreateFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonCreateFinal) {
                    createMatches();
                }
            }
        });

        swipeRefreshTab3 = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshTab3);
        swipeRefreshTab3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fragment fragment = Tab3.this;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.detach(fragment);
                fragmentTransaction.attach(fragment);
                fragmentTransaction.commit();
            }
        });


        Bundle data = getActivity().getIntent().getExtras();
        final int formato = data.getInt("formato");

        if (formato == 32) {
            textViewOctave.setText("Ottavi");
            recyclerViewOctave = (RecyclerView) view.findViewById(R.id.recyclerViewOctave);
            loadOctaveRecyclerData();
        }

        recyclerViewQuarter = (RecyclerView) view.findViewById(R.id.recyclerViewQuarter);
        loadQuarterRecyclerData();

        recyclerViewSemi = (RecyclerView) view.findViewById(R.id.recyclerViewSemi);
        loadSemiRecyclerData();

        recyclerViewFinal = (RecyclerView) view.findViewById(R.id.recyclerViewFinal);
        loadFinalRecyclerData();

        return view;
    }

    public void loadOctaveRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_OCTAVE_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("octave");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), -1, o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterOctave = new MatchAdapter(listMatches, getContext());
                                recyclerViewOctave.setAdapter(adapterOctave);
                                LinearLayoutManager managerO = new LinearLayoutManager(getContext());
                                recyclerViewOctave.setLayoutManager(managerO);
                                recyclerViewOctave.setHasFixedSize(true);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadQuarterRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_QUARTER_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("quarter");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), -2, o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterQuarter = new MatchAdapter(listMatches, getContext());
                                recyclerViewQuarter.setAdapter(adapterQuarter);
                                LinearLayoutManager managerQ = new LinearLayoutManager(getContext());
                                recyclerViewQuarter.setLayoutManager(managerQ);
                                recyclerViewQuarter.setHasFixedSize(true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadSemiRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_SEMI_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("semi");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), -3, o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterSemi = new MatchAdapter(listMatches, getContext());
                                recyclerViewSemi.setAdapter(adapterSemi);
                                LinearLayoutManager managerS = new LinearLayoutManager(getContext());
                                recyclerViewSemi.setLayoutManager(managerS);
                                recyclerViewSemi.setHasFixedSize(true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadFinalRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_FINAL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("final");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), -4, o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterFinal = new MatchAdapter(listMatches, getContext());
                                recyclerViewFinal.setAdapter(adapterFinal);
                                LinearLayoutManager managerF = new LinearLayoutManager(getContext());
                                recyclerViewFinal.setLayoutManager(managerF);
                                recyclerViewFinal.setHasFixedSize(true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }


    private void populateTeams() {

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        teams.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GET_FINAL_TEAMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext().getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void createMatches() {

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");
        final int format = data.getInt("formato");


            order.add(0);
            order.add(5);
            order.add(1);
            order.add(4);
            order.add(8);
            order.add(13);
            order.add(9);
            order.add(12);
            order.add(16);
            order.add(21);
            order.add(17);
            order.add(20);
            order.add(24);
            order.add(29);
            order.add(25);
            order.add(29);



        for (int k = 0; k < (format/4); k++) {

                final int t = k;
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_INSERT_MATCH_FINAL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getBoolean("error"))
                                        Toast.makeText(getContext().getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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

                                Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("formato", String.valueOf(format).trim());
                        params.put("id_torneo", String.valueOf(idTorneo).trim());
                        params.put("id_partita", String.valueOf(t).trim());
                        params.put("nome_squadra_1", teams.get(order.remove(0)));
                        params.put("nome_squadra_2", teams.get(order.remove(0)));
                        return params;

                    }
                };

                RequestHandler.getInstance(getContext().getApplicationContext()).addToRequestQueue(stringRequest);
            }


    }

}
