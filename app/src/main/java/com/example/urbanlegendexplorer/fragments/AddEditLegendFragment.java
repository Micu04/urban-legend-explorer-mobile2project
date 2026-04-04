package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.urbanlegendexplorer.R;

public class AddEditLegendFragment extends Fragment {

    public AddEditLegendFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_edit_legend, container, false);
    }
}