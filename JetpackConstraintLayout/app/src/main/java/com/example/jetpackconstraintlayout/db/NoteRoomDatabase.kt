package com.example.jetpackconstraintlayout.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackconstraintlayout.db.dao.NoteDao
import com.example.jetpackconstraintlayout.db.entity.NoteEntity
@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun notaDao() : NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    NoteRoomDatabase::class.java,
                    "notes_db"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}