package com.example.jetpackconstraintlayout

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.jetpackconstraintlayout.db.NoteRoomDatabase
import com.example.jetpackconstraintlayout.db.dao.NoteDao
import com.example.jetpackconstraintlayout.db.entity.NoteEntity

class NoteRepository {

    lateinit var noteDao: NoteDao
    lateinit var allNotes: LiveData<List<NoteEntity>>
    lateinit var favNotes: LiveData<List<NoteEntity>>

    fun NoteRepository(app : Application) {
        var db : NoteRoomDatabase = NoteRoomDatabase.getDatabase(app)
        noteDao = db.notaDao()
        allNotes = noteDao.getAll()
        favNotes = noteDao.getAllFavorites()
    }

    fun getAll() = allNotes
    fun getFavorites() = favNotes
    fun insert(note: NoteEntity) = InsertAsyncTask(noteDao).execute(note)


    private class InsertAsyncTask(var noteDaoAsyncTask : NoteDao) : AsyncTask<NoteEntity, Void, Void>() {

        override fun doInBackground(vararg notes: NoteEntity): Void? {
            noteDaoAsyncTask.create(notes[0])
            return null
        }
    }
}