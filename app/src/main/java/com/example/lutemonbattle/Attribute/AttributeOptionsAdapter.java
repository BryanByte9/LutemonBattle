package com.example.lutemonbattle.Attribute;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lutemonbattle.R;
import java.util.List;

public class AttributeOptionsAdapter extends RecyclerView.Adapter<AttributeOptionsAdapter.ViewHolder> {
    private final List<AttributeOption> dataList;
    private int selectedPosition = -1;
    private OnAttributeSelectedListener listener;

    public AttributeOptionsAdapter(List<AttributeOption> dataList) {
        this.dataList = dataList;
    }

    public interface OnAttributeSelectedListener {
        void onAttributeSelected(AttributeOption selectedOption);
    }

    public void setOnAttributeSelectedListener(OnAttributeSelectedListener listener) {
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attribute_option, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        AttributeOption item = dataList.get(position);

        if (item.getIconResId() != 0) {
            holder.ivAttributeIcon.setImageResource(item.getIconResId());
        }
        holder.tvAttributeInfo.setText(
                item.getAttributeName() + " | ATK:" + item.getAtk() + " DEF:" + item.getDef() + " API:" + item.getApi() + " HP:" + item.getHp());
        holder.rbSelect.setChecked(position == selectedPosition && selectedPosition != -1);

        holder.rbSelect.setOnClickListener(v -> handleRadioButtonClick(holder));
    }
    private void handleRadioButtonClick(ViewHolder holder) {
        if (selectedPosition != holder.getAdapterPosition()) {
            int previousSelectedPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousSelectedPosition);
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onAttributeSelected(dataList.get(selectedPosition));
            }
        }
    }

    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAttributeIcon;
        TextView tvAttributeInfo;
        RadioButton rbSelect;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAttributeIcon = itemView.findViewById(R.id.ivAttributeIcon);
            tvAttributeInfo = itemView.findViewById(R.id.tvAttributeInfo);
            rbSelect = itemView.findViewById(R.id.rbSelect);
        }
    }
}
