package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.urbanlegendexplorer.R;
import com.example.urbanlegendexplorer.model.Legend;
import com.example.urbanlegendexplorer.model.LegendCategory;
import com.example.urbanlegendexplorer.viewmodel.LegendViewModel;

public class LegendDetailFragment extends Fragment {

    private ImageView imageLegendDetail;
    private TextView textDetailTitle;
    private TextView textDetailCategory;
    private TextView textDetailLocation;
    private TextView textDetailDescription;
    private ImageButton buttonBack;
    private Button buttonEdit;
    private Button buttonDelete;

    private LegendViewModel legendViewModel;
    private Legend currentLegend;

    public LegendDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_legend_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imageLegendDetail = view.findViewById(R.id.imageLegendDetail);
        textDetailTitle = view.findViewById(R.id.textDetailTitle);
        textDetailCategory = view.findViewById(R.id.textDetailCategory);
        textDetailLocation = view.findViewById(R.id.textDetailLocation);
        textDetailDescription = view.findViewById(R.id.textDetailDescription);
        buttonBack = view.findViewById(R.id.buttonBackDetail);
        buttonEdit = view.findViewById(R.id.buttonEdit);
        buttonDelete = view.findViewById(R.id.buttonDelete);

        legendViewModel = new ViewModelProvider(requireActivity()).get(LegendViewModel.class);

        readLegendFromArguments();
        bindData();

        buttonBack.setOnClickListener(v ->
                Navigation.findNavController(view).navigateUp()
        );

        buttonEdit.setOnClickListener(v -> {
            if (currentLegend == null) {
                Toast.makeText(requireContext(), "Legend not loaded yet!", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString("legendId", currentLegend.getId());
            bundle.putString("title", currentLegend.getTitle());
            bundle.putString("category", currentLegend.getCategory());
            bundle.putString("location", currentLegend.getLocation());
            bundle.putString("description", currentLegend.getDescription());
            bundle.putString("imageUrl", currentLegend.getImageUrl());
            bundle.putLong("createdAt", currentLegend.getCreatedAt());

            Navigation.findNavController(view)
                    .navigate(R.id.action_legendDetailFragment_to_addEditLegendFragment, bundle);
        });

        buttonDelete.setOnClickListener(v -> {
            if (currentLegend == null) {
                Toast.makeText(requireContext(), "Legend not loaded yet!", Toast.LENGTH_SHORT).show();
                return;
            }

            legendViewModel.delete(currentLegend);
            Toast.makeText(requireContext(), "Legend deleted!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigateUp();
        });
    }

    private void readLegendFromArguments() {
        Bundle args = getArguments();

        if (args == null) {
            currentLegend = null;
            return;
        }

        String id = args.getString("legendId", "");
        String title = args.getString("title", "");
        String categoryString = args.getString("category", LegendCategory.OTHER.name());
        String location = args.getString("location", "");
        String description = args.getString("description", "");
        String imageUrl = args.getString("imageUrl", "");
        long createdAt = args.getLong("createdAt", System.currentTimeMillis());

        LegendCategory category;

        try {
            category = LegendCategory.valueOf(categoryString);
        } catch (IllegalArgumentException e) {
            category = LegendCategory.OTHER;
        }

        currentLegend = new Legend(
                id,
                title,
                category,
                location,
                description,
                imageUrl,
                createdAt
        );
    }

    private void bindData() {
        if (currentLegend == null) {
            return;
        }

        textDetailTitle.setText(currentLegend.getTitle());
        textDetailCategory.setText(currentLegend.getCategory());
        textDetailLocation.setText(currentLegend.getLocation());
        textDetailDescription.setText(currentLegend.getDescription());

        Glide.with(requireContext())
                .load(currentLegend.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageLegendDetail);
    }
}