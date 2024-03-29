package com.example.jetpackconstraintlayout.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.jetpackconstraintlayout.db.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun create(note : NoteEntity)

    @Update
    fun update(note : NoteEntity)

    @Query("DELETE FROM notas")
    fun deleteAll()

    @Query("DELETE FROM notas WHERE id = :idNota")
    fun deleteById(idNota : Int)

    @Query("SELECT * FROM notas ORDER BY title ASC")
    fun getAll() : LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notas WHERE fav LIKE 'true'")
    fun getAllFavorites() : LiveData<List<NoteEntity>>
}