package com.example.lutemonbattle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lutemonbattle.R;
import com.example.lutemonbattle.Lutemons.Lutemon;
import java.util.List;

public class LutemonStatsAdapter extends RecyclerView.Adapter<LutemonStatsAdapter.ViewHolder> {

    private final List<Lutemon> lutemonList;

    public LutemonStatsAdapter(List<Lutemon> lutemonList) {

        this.lutemonList = lutemonList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView lutemonImage;
        TextView lutemonName;
        TextView lutemontype;
        TextView tvBattles;
        TextView tvWins;
        TextView tvLost;
        TextView tvTrainings;

        public ViewHolder(View itemView) {
            super(itemView);
            lutemonImage = itemView.findViewById(R.id.imageView13);
            lutemonName = itemView.findViewById(R.id.tv_lutemon_name);
            lutemontype = itemView.findViewById(R.id.tv_lutemon_type);
            tvBattles = itemView.findViewById(R.id.tv_battles);
            tvWins = itemView.findViewById(R.id.tv_wins);
            tvLost = itemView.findViewById(R.id.tv_lost);
            tvTrainings = itemView.findViewById(R.id.tv_trainings);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lutemon_stat, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Lutemon lutemon = lutemonList.get(position);


        String attribute = lutemon.getAttribute().substring(0, 1).toUpperCase()
                + lutemon.getAttribute().substring(1);

        holder.lutemonImage.setImageResource(
                getIconResIdByAttribute(lutemon.getAttribute())
        );
        holder.lutemonName.setText(lutemon.getName());
        holder.lutemontype.setText(attribute);
        holder.tvBattles.setText(String.format("Battles\n%d", lutemon.getBattles()));
        holder.tvWins.setText(String.format("Wins\n%d", lutemon.getWins()));
        holder.tvLost.setText(String.format("Lost\n%d", lutemon.getBattles()-lutemon.getWins()));
        holder.tvTrainings.setText(String.format("Trainings\n%d", lutemon.getTraining()));
    }

    public int getItemCount() {
        return lutemonList.size();
    }

    //Set Image of Lutemon
    private int getIconResIdByAttribute(String attribute) {
        switch (attribute.toLowerCase()) {
            case "fire":
                return R.drawable.fire_dragon;
            case "water":
                return R.drawable.water_dragon;
            case "dark":
                return R.drawable.dark_dragon;
            case "wind":
                return R.drawable.wind_dragon;
            case "light":
                return R.drawable.light_dragon;
            case "rock":
                return R.drawable.rock_dragon;
            case "wood":
                return R.drawable.wood_dragon;
            default:
                return R.drawable.ic_default;
        }
    }

}




