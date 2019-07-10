package com.example.jetpackconstraintlayout

interface NotesInteractionListener {

    fun editNoteClick(note: Note)
    fun deleteNoteClick(note: Note)
    fun favNoteClick(note: Note)
}