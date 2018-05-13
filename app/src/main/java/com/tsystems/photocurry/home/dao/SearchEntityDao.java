package com.tsystems.photocurry.home.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tsystems.photocurry.home.model.SearchEntity;

import java.util.List;

/**
 * Created by PerryGarg on 13-05-2018.
 */

@Dao
public interface SearchEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchEntity searchEntity);

    @Query("DELETE FROM search_table")
    void deleteAll();

    @Query("SELECT * FROM search_table WHERE `query` = :queryText LIMIT 1")
    SearchEntity getSearchEntityWithQuery(String queryText);

}
