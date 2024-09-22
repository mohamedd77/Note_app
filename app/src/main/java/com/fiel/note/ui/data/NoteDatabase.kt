package com.fiel.note.ui.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.repository.Repository

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():Repository

}