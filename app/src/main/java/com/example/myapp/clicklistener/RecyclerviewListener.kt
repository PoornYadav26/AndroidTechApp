package com.example.myapp.clicklistener


import com.example.myapp.ui.apidatalist.model.EntryModel

interface RecyclerviewListener {
    fun onItemClick(item: EntryModel)
}