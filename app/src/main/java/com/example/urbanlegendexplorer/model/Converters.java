package com.example.urbanlegendexplorer.model;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static String fromCategory(LegendCategory category) {
        return category == null ? null : category.name();
    }

    @TypeConverter
    public static LegendCategory toCategory(String value) {
        return value == null ? LegendCategory.OTHER : LegendCategory.valueOf(value);
    }
}