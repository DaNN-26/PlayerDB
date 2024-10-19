package com.example.playerdb.mvi.main.entry

sealed class EntryIntent {
    class OnIdChange(val id: String) : EntryIntent()
    data object FindClick : EntryIntent()
}