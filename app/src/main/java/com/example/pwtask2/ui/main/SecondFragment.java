package com.example.pwtask2.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pwtask2.MainActivity;
import com.example.pwtask2.R;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View itemView =  inflater.inflate(R.layout.second_fragment, container, false);
        MainActivity activity = ((MainActivity) getActivity());
        Button addButton = itemView.findViewById(R.id.add_button);
        Button deleteButton = itemView.findViewById(R.id.delete_button);

        addButton.setOnClickListener(view -> {
            if (activity != null) {
                EditText editText = itemView.findViewById(R.id.second_frag_edit_text);
                if(editText != null){
                String editable = editText.getText().toString();
                    if (!TextUtils.isEmpty(editable)) {
                        activity.addItem(editable);
                    }
            }}
        });

        deleteButton.setOnClickListener(view -> {
            if (activity!=null)
                activity.deleteItems();
        });

        return itemView;
    }

}