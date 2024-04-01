package com.example.myapp.clicklistner


import com.example.myapp.ui.apilist.activity.model.EntryModel

interface RecyclerviewListner {
    fun onItemClick(item: EntryModel)
}