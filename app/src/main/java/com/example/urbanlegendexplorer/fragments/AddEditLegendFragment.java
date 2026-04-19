package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.urbanlegendexplorer.R;

public class AddEditLegendFragment extends Fragment {

    private EditText editTitle;
    private EditText editCategory;
    private EditText editLocation;
    private EditText editImageUrl;
    private EditText editDescription;
    private Button buttonSaveLegend;

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
        editCategory = view.findViewById(R.id.editCategory);
        editLocation = view.findViewById(R.id.editLocation);
        editImageUrl = view.findViewById(R.id.editImageUrl);
        editDescription = view.findViewById(R.id.editDescription);
        buttonSaveLegend = view.findViewById(R.id.buttonSaveLegend);

        Bundle args = getArguments();
        if (args != null) {
            editTitle.setText(args.getString("title", ""));
            editCategory.setText(args.getString("category", ""));
            editLocation.setText(args.getString("location", ""));
            editImageUrl.setText(args.getString("imageUrl", ""));
            editDescription.setText(args.getString("description", ""));
        }

        buttonSaveLegend.setOnClickListener(v -> saveLegend());
    }

    private void saveLegend() {
        String title = editTitle.getText().toString().trim();
        String category = editCategory.getText().toString().trim();
        String location = editLocation.getText().toString().trim();
        String imageUrl = editImageUrl.getText().toString().trim();
        String description = editDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(requireContext(), "Title and description are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(requireContext(), "Save will be connected to Firestore next", Toast.LENGTH_SHORT).show();
    }
}