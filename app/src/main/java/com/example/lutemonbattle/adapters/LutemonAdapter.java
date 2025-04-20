package com.example.lutemonbattle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {
    private List<Lutemon> lutemonList;
    private Set<Integer> selectedIds = new HashSet<>(); // Store Lutemon IDs that are selected

    public LutemonAdapter(List<Lutemon> lutemonList) {
        this.lutemonList = new ArrayList<>(lutemonList); // Use a new ArrayList to avoid modifying the original list
    }

    // Get the selected Lutemons
    public List<Lutemon> getSelectedLutemons() {
        List<Lutemon> selected = new ArrayList<>();
        for (Lutemon lutemon : lutemonList) {
            if (selectedIds.contains(lutemon.getId())) {
                selected.add(lutemon);
            }
        }
        return selected;
    }

    // Update
    public void updateData(List<Lutemon> newLutemons) {
        this.lutemonList = new ArrayList<>(newLutemons);
        notifyDataSetChanged();
    }


    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lutemon, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder( ViewHolder holder, int position) {
        Lutemon lutemon = lutemonList.get(position);

        // Bond with checkbox
        holder.cbSelect.setChecked(selectedIds.contains(lutemon.getId()));
        holder.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedIds.add(lutemon.getId());
            } else {
                selectedIds.remove(lutemon.getId());
            }
        });

        // Set text message
        holder.tvName.setText(lutemon.getName());
        holder.tvAttribute.setText(lutemon.getAttribute());
        holder.tvStats.setText(String.format(
                "ATK: %d DEF: %d HP: %d/%d",
                lutemon.getAtk(),
                lutemon.getDef(),
                lutemon.getCurrentHp(),
                lutemon.getMaxHealth()
        ));
        holder.tvExperience.setText("Experience: " + lutemon.getExperience());
        holder.tvImage.setImageResource(lutemon.getIconResId());

    }


    public int getItemCount() {
        return lutemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbSelect;
        TextView tvName, tvAttribute, tvStats, tvExperience;
        ImageView tvImage;

        public ViewHolder( View itemView) {
            super(itemView);
            cbSelect = itemView.findViewById(R.id.cbSelect);
            tvName = itemView.findViewById(R.id.tvName);
            tvAttribute = itemView.findViewById(R.id.tvAttribute);
            tvStats = itemView.findViewById(R.id.tvStats);
            tvExperience = itemView.findViewById(R.id.tvExperience);
            tvImage = itemView.findViewById(R.id.imageView16);
        }
    }
}