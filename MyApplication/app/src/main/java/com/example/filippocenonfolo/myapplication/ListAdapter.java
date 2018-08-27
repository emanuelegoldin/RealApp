package com.example.filippocenonfolo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import java.util.List;
import java.util.Map;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public ListAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ListItem listItem = listItems.get(i);

        final int creator = SharedPrefManager.getInstance(context.getApplicationContext()).getUserId();

        viewHolder.textViewHead.setText(listItem.getName());
        viewHolder.textViewDescription.setText(listItem.getPlace());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listItem.getComplete() != 0) {
                    if (listItem.getIdCreator() == creator) {
                        Intent i = new Intent(view.getContext().getApplicationContext(), NonCompleteTournamentActivity.class);
                        i.putExtra("titolo", "Numero squadre iscritte");
                        i.putExtra("testo", String.valueOf(listItem.getFormat()-listItem.getComplete()));
                        view.getContext().startActivity(i);
                    }
                    else {
                        nonComplete(listItem.getId(), creator);
                        Intent i2 = new Intent(view.getContext().getApplicationContext(), AddTeamActivity.class);
                        i2.putExtra("idTorneo", listItem.getId());
                        view.getContext().startActivity(i2);

                    }
                }
                else {
                    if (listItem.getPhase() == 0 && listItem.getIdCreator() == creator) {
                        Intent i3 = new Intent(view.getContext().getApplicationContext(), GenerateGroupsActivity.class);
                        i3.putExtra("idTorneo", listItem.getId());
                        i3.putExtra("formato", listItem.getFormat());
                        view.getContext().startActivity(i3);
                    }
                    else if (listItem.getPhase() == 0 && listItem.getIdCreator() != creator) {
                        nonComplete(listItem.getId(), creator);
                        Intent i4 = new Intent(view.getContext().getApplicationContext(), AddTeamActivity.class);
                        i4.putExtra("idTorneo", listItem.getId());
                        view.getContext().startActivity(i4);
                    }
                    else if (listItem.getPhase() > 0) {
                        Intent i5 = new Intent(view.getContext().getApplicationContext(), TournamentDataActivity.class);
                        i5.putExtra("idTorneo", listItem.getId());
                        i5.putExtra("formato", listItem.getFormat());
                        i5.putExtra("creatore", listItem.getIdCreator());
                        view.getContext().startActivity(i5);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDescription;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutTournaments);

        }
    }

    public void nonComplete(final int idTorneo, final int creator) {


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_TEAM_BY_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                Intent i = new Intent(context.getApplicationContext(), NonCompleteTournamentActivity.class);
                                i.putExtra("titolo", "Sei già iscritto con:");
                                i.putExtra("testo", obj.getString("team"));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.getApplicationContext().startActivity(i);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_torneo", String.valueOf(idTorneo).trim());
                params.put("creatore", String.valueOf(creator).trim());
                return params;
            }
        };

        RequestHandler.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
