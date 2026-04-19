package com.example.urbanlegendexplorer.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.urbanlegendexplorer.R;

public class LegendDetailFragment extends Fragment {

    private ImageView imageLegendDetail;
    private TextView textDetailCategory;
    private TextView textDetailTitle;
    private TextView textDetailLocation;
    private TextView textDetailDescription;
    private Button buttonEdit;
    private Button buttonDelete;

    private String currentImageUrl = "";

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
        textDetailCategory = view.findViewById(R.id.textDetailCategory);
        textDetailTitle = view.findViewById(R.id.textDetailTitle);
        textDetailLocation = view.findViewById(R.id.textDetailLocation);
        textDetailDescription = view.findViewById(R.id.textDetailDescription);
        buttonEdit = view.findViewById(R.id.buttonEdit);
        buttonDelete = view.findViewById(R.id.buttonDelete);

        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString("title", "");
            String category = args.getString("category", "");
            String location = args.getString("location", "");
            String description = args.getString("description", "");
            String imageUrl = args.getString("imageUrl", "");

            currentImageUrl = imageUrl;

            textDetailTitle.setText(title);
            textDetailCategory.setText(category);
            textDetailLocation.setText(location);
            textDetailDescription.setText(description);

            if (!TextUtils.isEmpty(imageUrl)) {
                Glide.with(requireContext())
                        .load(imageUrl)
                        .placeholder(android.R.color.darker_gray)
                        .into(imageLegendDetail);
            }
        }

        buttonEdit.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", textDetailTitle.getText().toString());
            bundle.putString("category", textDetailCategory.getText().toString());
            bundle.putString("location", textDetailLocation.getText().toString());
            bundle.putString("description", textDetailDescription.getText().toString());
            bundle.putString("imageUrl", currentImageUrl);

            AddEditLegendFragment fragment = new AddEditLegendFragment();
            fragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        buttonDelete.setOnClickListener(v -> {
            // később ide jön a firestore törlés
        });
    }
}