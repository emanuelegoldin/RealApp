package com.example.filippocenonfolo.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class Tab2 extends Fragment {


    private RecyclerView recyclerViewRankA;
    private RecyclerView recyclerViewRankB;
    private RecyclerView recyclerViewRankC;
    private RecyclerView recyclerViewRankD;
    private RecyclerView recyclerViewRankE;
    private RecyclerView recyclerViewRankF;
    private RecyclerView recyclerViewRankG;
    private RecyclerView recyclerViewRankH;
    private RecyclerView.Adapter adapterRankA;
    private RecyclerView.Adapter adapterRankB;
    private RecyclerView.Adapter adapterRankC;
    private RecyclerView.Adapter adapterRankD;
    private RecyclerView.Adapter adapterRankE;
    private RecyclerView.Adapter adapterRankF;
    private RecyclerView.Adapter adapterRankG;
    private RecyclerView.Adapter adapterRankH;
    private TextView textViewRankE;
    private TextView textViewRankF;
    private TextView textViewRankG;
    private TextView textViewRankH;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container,false);

        textViewRankE = (TextView) view.findViewById(R.id.textViewRankE);
        textViewRankF = (TextView) view.findViewById(R.id.textViewRankF);
        textViewRankG = (TextView) view.findViewById(R.id.textViewRankG);
        textViewRankH = (TextView) view.findViewById(R.id.textViewRankH);

        Bundle data = getActivity().getIntent().getExtras();
        final int formato = data.getInt("formato");

        recyclerViewRankA = (RecyclerView) view.findViewById(R.id.recyclerViewRankA);
        loadRankARecyclerData();

        recyclerViewRankB = (RecyclerView) view.findViewById(R.id.recyclerViewRankB);
        loadRankBRecyclerData();

        recyclerViewRankC = (RecyclerView) view.findViewById(R.id.recyclerViewRankC);
        loadRankCRecyclerData();

        recyclerViewRankD = (RecyclerView) view.findViewById(R.id.recyclerViewRankD);
        loadRankDRecyclerData();

       if (formato == 32) {

           textViewRankE.setText("Girone E");
           recyclerViewRankE = (RecyclerView) view.findViewById(R.id.recyclerViewRankE);
           loadRankERecyclerData();

           textViewRankF.setText("Girone F");
           recyclerViewRankF = (RecyclerView) view.findViewById(R.id.recyclerViewRankF);
           loadRankFRecyclerData();

           textViewRankG.setText("Girone G");
           recyclerViewRankG = (RecyclerView) view.findViewById(R.id.recyclerViewRankG);
           loadRankGRecyclerData();

           textViewRankH.setText("Girone H");
           recyclerViewRankH = (RecyclerView) view.findViewById(R.id.recyclerViewRankH);
           loadRankHRecyclerData();
       }

        return view;
    }

    public void loadRankARecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankA = new RankAdapter(listRanks, getContext());
                                recyclerViewRankA.setAdapter(adapterRankA);
                                LinearLayoutManager managerRA = new LinearLayoutManager(getContext());
                                recyclerViewRankA.setLayoutManager(managerRA);
                                recyclerViewRankA.setHasFixedSize(true);
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

    public void loadRankBRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankB = new RankAdapter(listRanks, getContext());
                                recyclerViewRankB.setAdapter(adapterRankB);
                                LinearLayoutManager managerRB = new LinearLayoutManager(getContext());
                                recyclerViewRankB.setLayoutManager(managerRB);
                                recyclerViewRankB.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(1).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadRankCRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankC = new RankAdapter(listRanks, getContext());
                                recyclerViewRankC.setAdapter(adapterRankC);
                                LinearLayoutManager managerRC = new LinearLayoutManager(getContext());
                                recyclerViewRankC.setLayoutManager(managerRC);
                                recyclerViewRankC.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(2).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadRankDRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankD = new RankAdapter(listRanks, getContext());
                                recyclerViewRankD.setAdapter(adapterRankD);
                                LinearLayoutManager managerRD = new LinearLayoutManager(getContext());
                                recyclerViewRankD.setLayoutManager(managerRD);
                                recyclerViewRankD.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(3).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadRankERecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankE = new RankAdapter(listRanks, getContext());
                                recyclerViewRankE.setAdapter(adapterRankE);
                                LinearLayoutManager managerRE = new LinearLayoutManager(getContext());
                                recyclerViewRankE.setLayoutManager(managerRE);
                                recyclerViewRankE.setHasFixedSize(true);
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

    public void loadRankFRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankF = new RankAdapter(listRanks, getContext());
                                recyclerViewRankF.setAdapter(adapterRankF);
                                LinearLayoutManager managerRF = new LinearLayoutManager(getContext());
                                recyclerViewRankF.setLayoutManager(managerRF);
                                recyclerViewRankF.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(5).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadRankGRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankG = new RankAdapter(listRanks, getContext());
                                recyclerViewRankG.setAdapter(adapterRankG);
                                LinearLayoutManager managerRG = new LinearLayoutManager(getContext());
                                recyclerViewRankG.setLayoutManager(managerRG);
                                recyclerViewRankG.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(6).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void loadRankHRecyclerData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        Bundle data = getActivity().getIntent().getExtras();
        final int idTorneo = data.getInt("idTorneo");

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Rank>listRanks = new ArrayList<>();
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rank");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                Rank rank = new Rank(o.getString("nome_squadra"), o.getInt("punteggio_squadra"));
                                listRanks.add(rank);
                                adapterRankH = new RankAdapter(listRanks, getContext());
                                recyclerViewRankH.setAdapter(adapterRankH);
                                LinearLayoutManager managerRH = new LinearLayoutManager(getContext());
                                recyclerViewRankH.setLayoutManager(managerRH);
                                recyclerViewRankH.setHasFixedSize(true);
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
                params.put("id_girone", String.valueOf(7).trim());
                return params;

            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }


}
