package com.example.filippocenonfolo.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Tab1 extends Fragment {

    private SwipeRefreshLayout swipeRefreshTab1;
    private RecyclerView recyclerViewGroupA;
    private RecyclerView recyclerViewGroupB;
    private RecyclerView recyclerViewGroupC;
    private RecyclerView recyclerViewGroupD;
    private RecyclerView recyclerViewGroupE;
    private RecyclerView recyclerViewGroupF;
    private RecyclerView recyclerViewGroupG;
    private RecyclerView recyclerViewGroupH;
    private RecyclerView.Adapter adapterGroupA;
    private RecyclerView.Adapter adapterGroupB;
    private RecyclerView.Adapter adapterGroupC;
    private RecyclerView.Adapter adapterGroupD;
    private RecyclerView.Adapter adapterGroupE;
    private RecyclerView.Adapter adapterGroupF;
    private RecyclerView.Adapter adapterGroupG;
    private RecyclerView.Adapter adapterGroupH;
    private TextView textViewGroupE;
    private TextView textViewGroupF;
    private TextView textViewGroupG;
    private TextView textViewGroupH;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container,false);

        textViewGroupE = (TextView) view.findViewById(R.id.textViewGroupE);
        textViewGroupF = (TextView) view.findViewById(R.id.textViewGroupF);
        textViewGroupG = (TextView) view.findViewById(R.id.textViewGroupG);
        textViewGroupH = (TextView) view.findViewById(R.id.textViewGroupH);



        swipeRefreshTab1 = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshTab1);
        swipeRefreshTab1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fragment fragment = Tab1.this;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.detach(fragment);
                fragmentTransaction.attach(fragment);
                fragmentTransaction.commit();
            }
        });


        Bundle data = getActivity().getIntent().getExtras();
        final int formato = data.getInt("formato");


        recyclerViewGroupA = (RecyclerView) view.findViewById(R.id.recyclerViewGroupA);
        loadGroupARecyclerData();

        recyclerViewGroupB = (RecyclerView) view.findViewById(R.id.recyclerViewGroupB);
        loadGroupBRecyclerData();

        recyclerViewGroupC = (RecyclerView) view.findViewById(R.id.recyclerViewGroupC);
        loadGroupCRecyclerData();

        recyclerViewGroupD = (RecyclerView) view.findViewById(R.id.recyclerViewGroupD);
        loadGroupDRecyclerData();

        if(formato == 32) {

            textViewGroupE.setText("Girone E");
            recyclerViewGroupE = (RecyclerView) view.findViewById(R.id.recyclerViewGroupE);
            loadGroupERecyclerData();

            textViewGroupF.setText("Girone F");
            recyclerViewGroupF = (RecyclerView) view.findViewById(R.id.recyclerViewGroupF);
            loadGroupFRecyclerData();

            textViewGroupG.setText("Girone G");
            recyclerViewGroupG = (RecyclerView) view.findViewById(R.id.recyclerViewGroupG);
            loadGroupGRecyclerData();

            textViewGroupH.setText("Girone H");
            recyclerViewGroupH = (RecyclerView) view.findViewById(R.id.recyclerViewGroupH);
            loadGroupHRecyclerData();
        }

        return view;
    }

    public void loadGroupARecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupA = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupA.setAdapter(adapterGroupA);
                                LinearLayoutManager managerGA = new LinearLayoutManager(getContext());
                                recyclerViewGroupA.setLayoutManager(managerGA);
                                recyclerViewGroupA.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(0).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupBRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupB = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupB.setAdapter(adapterGroupB);
                                LinearLayoutManager managerGB = new LinearLayoutManager(getContext());
                                recyclerViewGroupB.setLayoutManager(managerGB);
                                recyclerViewGroupB.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(1).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupCRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupC = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupC.setAdapter(adapterGroupC);
                                LinearLayoutManager managerGC = new LinearLayoutManager(getContext());
                                recyclerViewGroupC.setLayoutManager(managerGC);
                                recyclerViewGroupC.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(2).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupDRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupD = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupD.setAdapter(adapterGroupD);
                                LinearLayoutManager managerGD = new LinearLayoutManager(getContext());
                                recyclerViewGroupD.setLayoutManager(managerGD);
                                recyclerViewGroupD.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(3).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupERecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupE = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupE.setAdapter(adapterGroupE);
                                LinearLayoutManager managerGE = new LinearLayoutManager(getContext());
                                recyclerViewGroupE.setLayoutManager(managerGE);
                                recyclerViewGroupE.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(4).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupFRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupF = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupF.setAdapter(adapterGroupF);
                                LinearLayoutManager managerGF = new LinearLayoutManager(getContext());
                                recyclerViewGroupF.setLayoutManager(managerGF);
                                recyclerViewGroupF.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(5).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupGRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupG = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupG.setAdapter(adapterGroupG);
                                LinearLayoutManager managerGG = new LinearLayoutManager(getContext());
                                recyclerViewGroupG.setLayoutManager(managerGG);
                                recyclerViewGroupG.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(6).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadGroupHRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GROUP_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Match>listMatches = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("group");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Match match = new Match(o.getInt("id_torneo"), o.getInt("id_girone"), o.getInt("id_partita"), o.getString("nome_squadra_1"), o.getString("nome_squadra_2"), o.getInt("gol_squadra_1"), o.getInt("gol_squadra_2"));
                                listMatches.add(match);
                                adapterGroupH = new MatchAdapter(listMatches, getContext());
                                recyclerViewGroupH.setAdapter(adapterGroupH);
                                LinearLayoutManager managerGH = new LinearLayoutManager(getContext());
                                recyclerViewGroupH.setLayoutManager(managerGH);
                                recyclerViewGroupH.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(7).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
