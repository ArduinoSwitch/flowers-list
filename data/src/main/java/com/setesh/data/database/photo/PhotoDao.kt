package com.setesh.data.database.photo

import androidx.room.Dao
import androidx.room.Query
import com.setesh.data.database.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao: BaseDao<PhotoEntity> {
    @Query("SELECT * FROM photo")
    fun getAllPhotos(): Flow<List<PhotoEntity>>

    @Query("SELECT ROUND(COUNT(id)/10) FROM photo")
    suspend fun getPhotoCount(): Int
}
