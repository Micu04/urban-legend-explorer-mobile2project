package com.example.urbanlegendexplorer.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.urbanlegendexplorer.model.Legend;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirebaseLegendRepository {

    private final FirebaseFirestore firestore;
    private final MutableLiveData<List<Legend>> legendsLiveData = new MutableLiveData<>();

    public FirebaseLegendRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public LiveData<List<Legend>> getLegendsFromFirestore() {
        firestore.collection("legends")
                .orderBy("createdAt")
                .addSnapshotListener((value, error) -> {
                    if (error != null || value == null) {
                        return;
                    }

                    List<Legend> legends = value.toObjects(Legend.class);
                    legendsLiveData.setValue(legends);
                });

        return legendsLiveData;
    }

    public void insertLegend(Legend legend) {
        firestore.collection("legends")
                .document(legend.getId())
                .set(legend);
    }

    public void updateLegend(Legend legend) {
        firestore.collection("legends")
                .document(legend.getId())
                .set(legend);
    }

    public void deleteLegend(Legend legend) {
        firestore.collection("legends")
                .document(legend.getId())
                .delete();
    }
}
