package com.example.urbanlegendexplorer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.urbanlegendexplorer.model.Legend;
import com.example.urbanlegendexplorer.repository.FirebaseLegendRepository;
import com.example.urbanlegendexplorer.repository.LegendRepository;

import java.util.List;

public class LegendViewModel extends AndroidViewModel {

    private final LegendRepository roomRepository;
    private final FirebaseLegendRepository firebaseRepository;

    private final LiveData<List<Legend>> allLegends;

    public LegendViewModel(@NonNull Application application) {
        super(application);

        roomRepository = new LegendRepository(application);
        firebaseRepository = new FirebaseLegendRepository();

        allLegends = firebaseRepository.getLegendsFromFirestore();
    }

    public LiveData<List<Legend>> getAllLegends() {
        return allLegends;
    }

    public LiveData<Legend> getLegendById(String id) {
        return roomRepository.getLegendById(id);
    }

    public void insert(Legend legend) {
        roomRepository.insert(legend);
        firebaseRepository.insertLegend(legend);
    }

    public void update(Legend legend) {
        roomRepository.update(legend);
        firebaseRepository.updateLegend(legend);
    }

    public void delete(Legend legend) {
        roomRepository.delete(legend);
        firebaseRepository.deleteLegend(legend);
    }

    public void deleteAll() {
        roomRepository.deleteAll();
    }
}