package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.urbanlegendexplorer.R;

public class LegendDetailFragment extends Fragment {

    public LegendDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_legend_detail, container, false);
    }
}