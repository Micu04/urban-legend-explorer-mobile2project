package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.urbanlegendexplorer.R;
import com.example.urbanlegendexplorer.model.Legend;
import com.example.urbanlegendexplorer.model.LegendCategory;
import com.example.urbanlegendexplorer.viewmodel.LegendViewModel;

import java.util.UUID;

public class AddEditLegendFragment extends Fragment {

    private EditText editTitle, editLocation, editDescription, editImageUrl;
    private Spinner spinnerCategory;
    private Button buttonSave;
    private ImageButton buttonBackAddEdit;

    private LegendViewModel legendViewModel;
    private String legendId = null;
    private long createdAt = 0L;

    public AddEditLegendFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit_legend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTitle = view.findViewById(R.id.editTitle);
        editLocation = view.findViewById(R.id.editLocation);
        editDescription = view.findViewById(R.id.editDescription);
        editImageUrl = view.findViewById(R.id.editImageUrl);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonBackAddEdit = view.findViewById(R.id.buttonBackAddEdit);

        legendViewModel = new ViewModelProvider(requireActivity()).get(LegendViewModel.class);

        setupSpinner();
        loadIfEditing();

        buttonBackAddEdit.setOnClickListener(v ->
                Navigation.findNavController(view).navigateUp()
        );

        buttonSave.setOnClickListener(v -> saveLegend(view));
    }

    private void setupSpinner() {
        ArrayAdapter<LegendCategory> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                LegendCategory.values()
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void loadIfEditing() {
        Bundle args = getArguments();

        if (args == null) {
            return;
        }

        legendId = args.getString("legendId", null);

        if (legendId == null) {
            return;
        }

        createdAt = args.getLong("createdAt", System.currentTimeMillis());

        editTitle.setText(args.getString("title", ""));
        editLocation.setText(args.getString("location", ""));
        editDescription.setText(args.getString("description", ""));
        editImageUrl.setText(args.getString("imageUrl", ""));

        String categoryString = args.getString("category", LegendCategory.OTHER.name());

        try {
            LegendCategory category = LegendCategory.valueOf(categoryString);
            spinnerCategory.setSelection(category.ordinal());
        } catch (IllegalArgumentException e) {
            spinnerCategory.setSelection(LegendCategory.OTHER.ordinal());
        }
    }

    private void saveLegend(View view) {
        String title = editTitle.getText().toString().trim();
        String location = editLocation.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String imageUrl = editImageUrl.getText().toString().trim();
        LegendCategory category = (LegendCategory) spinnerCategory.getSelectedItem();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(requireContext(), "Title and Description required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (legendId == null) {
            legendId = UUID.randomUUID().toString();
            createdAt = System.currentTimeMillis();

            Legend newLegend = new Legend(
                    legendId,
                    title,
                    category,
                    location,
                    description,
                    imageUrl,
                    createdAt
            );

            legendViewModel.insert(newLegend);
            Toast.makeText(requireContext(), "Legend saved!", Toast.LENGTH_SHORT).show();
        } else {
            Legend updatedLegend = new Legend(
                    legendId,
                    title,
                    category,
                    location,
                    description,
                    imageUrl,
                    createdAt
            );

            legendViewModel.update(updatedLegend);
            Toast.makeText(requireContext(), "Legend updated!", Toast.LENGTH_SHORT).show();
        }

        Navigation.findNavController(view).navigateUp();
    }
}