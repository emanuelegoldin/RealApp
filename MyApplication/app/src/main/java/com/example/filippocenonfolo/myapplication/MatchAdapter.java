package com.example.filippocenonfolo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private List<Match> listMatches;
    private Context context;

    public MatchAdapter(List<Match> listMatches, Context context) {
        this.listMatches = listMatches;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Match match = listMatches.get(i);

        viewHolder.textViewTeam1.setText(match.getTeam1());
        viewHolder.textViewTeam2.setText(match.getTeam2());

        if (match.getGolTeam1() == -1)
            viewHolder.textViewGol1.setText("-");
        else
            viewHolder.textViewGol1.setText(String.valueOf(match.getGolTeam1()));
        if (match.getGolTeam2() == -1)
            viewHolder.textViewGol2.setText("-");
        else
            viewHolder.textViewGol2.setText(String.valueOf(match.getGolTeam2()));

        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(view.getContext().getApplicationContext(), UpdateMatchActivity.class);
                i.putExtra("idTorneo", match.getIdTorneo());
                i.putExtra("idGirone", match.getIdGirone());
                i.putExtra("idPartita", match.getIdMatch());
                i.putExtra("squadra1", match.getTeam1());
                i.putExtra("squadra2", match.getTeam2());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMatches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTeam1;
        public TextView textViewTeam2;
        public TextView textViewGol1;
        public TextView textViewGol2;
        public ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTeam1 = (TextView) itemView.findViewById(R.id.textViewTeam1);
            textViewTeam2 = (TextView) itemView.findViewById(R.id.textViewTeam2);
            textViewGol1 = (TextView) itemView.findViewById(R.id.textViewGol1);
            textViewGol2 = (TextView) itemView.findViewById(R.id.textViewGol2);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraintLayoutMatch);


        }
    }


}
