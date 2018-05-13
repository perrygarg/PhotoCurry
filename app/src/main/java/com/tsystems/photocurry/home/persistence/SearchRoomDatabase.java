package com.tsystems.photocurry.home.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.home.converters.Converters;
import com.tsystems.photocurry.home.dao.SearchEntityDao;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.model.SearchEntity;

/**
 * Created by PerryGarg on 13-05-2018.
 */

@Database(entities = {SearchEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SearchRoomDatabase extends RoomDatabase {

    public abstract SearchEntityDao searchEntityDao();

    private static SearchRoomDatabase INSTANCE;

    public static SearchRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SearchRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SearchRoomDatabase.class, AppConstants.DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
