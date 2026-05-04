package com.example.urbanlegendexplorer.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanlegendexplorer.R;
import com.example.urbanlegendexplorer.adapter.LegendAdapter;
import com.example.urbanlegendexplorer.model.Legend;
import com.example.urbanlegendexplorer.viewmodel.LegendViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LegendListFragment extends Fragment {

    private RecyclerView recyclerViewLegends;
    private EditText editSearch;
    private Button buttonAddLegend;
    private Button buttonSortAZ;
    private Button buttonSortCategory;
    private Button buttonSortRecent;

    private LegendAdapter adapter;
    private LegendViewModel legendViewModel;

    private final List<Legend> allLegends = new ArrayList<>();
    private final List<Legend> filteredLegends = new ArrayList<>();

    private enum SortMode {
        AZ,
        CATEGORY,
        RECENT
    }

    private SortMode currentSortMode = SortMode.RECENT;
    private String currentSearchQuery = "";

    public LegendListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_legend_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerViewLegends = view.findViewById(R.id.recyclerViewLegends);
        editSearch = view.findViewById(R.id.editSearch);
        buttonAddLegend = view.findViewById(R.id.buttonAddLegend);
        buttonSortAZ = view.findViewById(R.id.buttonSortAZ);
        buttonSortCategory = view.findViewById(R.id.buttonSortCategory);
        buttonSortRecent = view.findViewById(R.id.buttonSortRecent);

        recyclerViewLegends.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new LegendAdapter(filteredLegends, legend -> {
            Bundle bundle = new Bundle();

            bundle.putString("legendId", legend.getId());
            bundle.putString("title", legend.getTitle());
            bundle.putString("category", legend.getCategory());
            bundle.putString("location", legend.getLocation());
            bundle.putString("description", legend.getDescription());
            bundle.putString("imageUrl", legend.getImageUrl());
            bundle.putLong("createdAt", legend.getCreatedAt());

            Navigation.findNavController(view)
                    .navigate(R.id.action_legendListFragment_to_legendDetailFragment, bundle);
        });

        recyclerViewLegends.setAdapter(adapter);

        legendViewModel = new ViewModelProvider(this).get(LegendViewModel.class);

        legendViewModel.getAllLegends().observe(getViewLifecycleOwner(), legends -> {
            allLegends.clear();

            if (legends != null) {
                allLegends.addAll(legends);
            }

            applyFilter(currentSearchQuery);
        });

        buttonAddLegend.setOnClickListener(v ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_legendListFragment_to_addEditLegendFragment)
        );

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearchQuery = s.toString();
                applyFilter(currentSearchQuery);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        buttonSortAZ.setOnClickListener(v -> {
            currentSortMode = SortMode.AZ;
            sortCurrentList();
            updateSortButtons();
        });

        buttonSortCategory.setOnClickListener(v -> {
            currentSortMode = SortMode.CATEGORY;
            sortCurrentList();
            updateSortButtons();
        });

        buttonSortRecent.setOnClickListener(v -> {
            currentSortMode = SortMode.RECENT;
            sortCurrentList();
            updateSortButtons();
        });

        updateSortButtons();
    }

    private void applyFilter(String query) {
        filteredLegends.clear();

        String lowerQuery = query.toLowerCase().trim();

        for (Legend legend : allLegends) {
            if (legend.getTitle().toLowerCase().contains(lowerQuery)
                    || legend.getCategory().toLowerCase().contains(lowerQuery)
                    || legend.getLocation().toLowerCase().contains(lowerQuery)) {
                filteredLegends.add(legend);
            }
        }

        sortCurrentList();
    }

    private void sortCurrentList() {
        switch (currentSortMode) {
            case AZ:
                filteredLegends.sort(Comparator.comparing(
                        Legend::getTitle,
                        String.CASE_INSENSITIVE_ORDER
                ));
                break;

            case CATEGORY:
                filteredLegends.sort(Comparator.comparing(
                        Legend::getCategory,
                        String.CASE_INSENSITIVE_ORDER
                ));
                break;

            case RECENT:
                filteredLegends.sort((a, b) -> Long.compare(b.getCreatedAt(), a.getCreatedAt()));
                break;
        }

        adapter.notifyDataSetChanged();
    }

    private void updateSortButtons() {
        setButtonSelectedStyle(buttonSortAZ, currentSortMode == SortMode.AZ);
        setButtonSelectedStyle(buttonSortCategory, currentSortMode == SortMode.CATEGORY);
        setButtonSelectedStyle(buttonSortRecent, currentSortMode == SortMode.RECENT);
    }

    private void setButtonSelectedStyle(Button button, boolean isSelected) {
        if (isSelected) {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F2F2F2")));
            button.setTextColor(Color.parseColor("#000000"));
        } else {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#111111")));
            button.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}