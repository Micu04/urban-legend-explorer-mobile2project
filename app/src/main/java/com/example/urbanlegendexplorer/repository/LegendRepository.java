package com.example.urbanlegendexplorer.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.urbanlegendexplorer.database.LegendDao;
import com.example.urbanlegendexplorer.database.LegendDatabase;
import com.example.urbanlegendexplorer.model.Legend;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LegendRepository {

    private final LegendDao legendDao;
    private final LiveData<List<Legend>> allLegends;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public LegendRepository(Application application) {
        LegendDatabase db = LegendDatabase.getInstance(application);
        legendDao = db.legendDao();
        allLegends = legendDao.getAllLegends();
    }

    public LiveData<List<Legend>> getAllLegends() {
        return allLegends;
    }

    public LiveData<Legend> getLegendById(String id) {
        return legendDao.getLegendById(id);
    }

    public void insert(Legend legend) {
        executor.execute(() -> legendDao.insert(legend));
    }

    public void update(Legend legend) {
        executor.execute(() -> legendDao.update(legend));
    }

    public void delete(Legend legend) {
        executor.execute(() -> legendDao.delete(legend));
    }

    public void deleteAll() {
        executor.execute(legendDao::deleteAll);
    }
}