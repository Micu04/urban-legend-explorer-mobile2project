package com.example.urbanlegendexplorer.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.urbanlegendexplorer.model.Converters;
import com.example.urbanlegendexplorer.model.Legend;

@Database(entities = {Legend.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class LegendDatabase extends RoomDatabase {

    private static volatile LegendDatabase INSTANCE;

    public abstract LegendDao legendDao();

    public static LegendDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LegendDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    LegendDatabase.class,
                                    "legend_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}