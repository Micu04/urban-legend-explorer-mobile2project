package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanlegendexplorer.R;

public class LegendListFragment extends Fragment {

    public LegendListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_legend_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLegends);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // TODO: később ide jön az adapter
        // recyclerView.setAdapter(...);

        return view;
    }
}