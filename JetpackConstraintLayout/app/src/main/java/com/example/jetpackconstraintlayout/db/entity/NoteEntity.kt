package com.example.jetpackconstraintlayout.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
data class NoteEntity (

    @PrimaryKey (autoGenerate = true)
    var id : Int? = null,
    var title : String,
    var content : String,
    var fav : Boolean,
    var color : String
)