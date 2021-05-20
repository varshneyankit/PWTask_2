package com.example.pwtask2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwtask2.R;
import com.example.pwtask2.adapter.ItemAdpater;
import com.example.pwtask2.adapter.ItemMoveCallback;

import java.util.LinkedList;
import java.util.List;

public class MainFragment extends Fragment {

    private final List<String> itemsList = new LinkedList<>();
    private ItemAdpater itemAdpater;
    private RecyclerView itemRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.main_fragment, container, false);
        itemRecyclerView = itemView.findViewById(R.id.item_recycler_view);
        itemAdpater = new ItemAdpater(itemsList);
        ItemTouchHelper.Callback callback = new ItemMoveCallback(itemAdpater);
        ItemTouchHelper touchHelper;touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(itemRecyclerView);
        itemRecyclerView.setAdapter(itemAdpater);

        return itemView;
    }

    public void addItem(String item) {
        if(!itemsList.contains(item)) {
            itemsList.add(item);
            int position = itemsList.size() - 1;
            itemRecyclerView.scrollToPosition(position);
            itemAdpater.notifyItemInserted(position);
        }else
            Toast.makeText(getActivity(),"Data Already Exists.",Toast.LENGTH_SHORT).show();
    }

    public void deleteItems() {
        itemAdpater.onRemoveCheckedItems();
        itemAdpater.notifyDataSetChanged();
    }

}