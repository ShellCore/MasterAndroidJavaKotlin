package com.example.jetpackconstraintlayout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotaRoomDatabase : RoomDatabase() {

    @Volatile
    private var INSTANCE: NotaRoomDatabase? = null

    fun getDatabase(context: Context): NotaRoomDatabase {
        if (INSTANCE == null) {
            synchronized(NotaRoomDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, NotaRoomDatabase::class.java, "notes_db")
                        .build()
                }
            }
        }
        return INSTANCE!!
    }

    abstract fun notaDao() : NoteDao
}