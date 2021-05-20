package com.example.pwtask2.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwtask2.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ItemAdpater extends RecyclerView.Adapter<ItemAdpater.ViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    private final List<String> itemsList;
    private final List<String> checkedItemList = new LinkedList<>();


    public ItemAdpater(List<String> itemsList) {
        this.itemsList = itemsList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemCheckBox.setText(itemsList.get(position));
        holder.itemCheckBox.setChecked(false);
        holder.itemCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                checkedItemList.add(compoundButton.getText().toString());
            else
                checkedItemList.remove(compoundButton.getText().toString());
        });

        holder.imageView.setOnClickListener(view -> Toast.makeText(view.getContext(), "Press and drag an item to change order", Toast.LENGTH_SHORT).show());

    }

    public void onRemoveCheckedItems() {
        for (String item : checkedItemList) {
            itemsList.remove(item);
        }
        checkedItemList.clear();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(itemsList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(itemsList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(ViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(ViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemCheckBox;
        ImageView imageView;
        View rowView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowView = itemView;
            itemCheckBox = itemView.findViewById(R.id.item_checkbox);
            imageView = itemView.findViewById(R.id.item_move_image);
        }
    }
}