package com.example.jetpackconstraintlayout.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.jetpackconstraintlayout.NoteRepository
import com.example.jetpackconstraintlayout.db.entity.NoteEntity

class NewNoteDialogViewModel(application: Application) : AndroidViewModel(application) {

    private var noteRepository: NoteRepository = NoteRepository(application)
    private var notes : LiveData<List<NoteEntity>>

    init {
        notes = noteRepository.getAll()
    }

    // El fragmento que necesita recibir la lista de datos
    fun getAllNotes() = notes

    // El fragment que inserte una nueva nota, deerá comunicarlo a este ViewModel
    fun insertNote(note: NoteEntity) = noteRepository.insert(note)

    fun updatetNote(note: NoteEntity) = noteRepository.update(note)
}
