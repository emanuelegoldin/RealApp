package com.example.filippocenonfolo.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private List<Rank> listRanks;
    private Context context;

    public RankAdapter(List<Rank> listRanks, Context context) {
        this.listRanks = listRanks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rank, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Rank rank = listRanks.get(i);

        viewHolder.textViewRankTeam.setText(rank.getTeam());
        viewHolder.textViewRankPoints.setText(String.valueOf(rank.getPoints()));
    }

    @Override
    public int getItemCount() {
        return listRanks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewRankTeam;
        public TextView textViewRankPoints;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewRankTeam = (TextView) itemView.findViewById(R.id.textViewRankTeam);
            textViewRankPoints = (TextView) itemView.findViewById(R.id.textViewRankPoints);

        }
    }


}
