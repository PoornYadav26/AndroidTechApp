package com.example.myapp.ui.apidatalist.model


import com.google.gson.annotations.SerializedName

data class ApiListResultWrapper(
    @SerializedName("count")
    val count: Int,
    @SerializedName("entries")
    val entries: MutableList<EntryModel>
)

data class EntryModel(
    @SerializedName("API")
    val aPI: String?=null,
    @SerializedName("Auth")
    val auth: String?=null,
    @SerializedName("Category")
    val category: String?=null,
    @SerializedName("Cors")
    val cors: String?=null,
    @SerializedName("Description")
    val description: String?=null,
    @SerializedName("HTTPS")
    val hTTPS: Boolean?=null,
    @SerializedName("Link")
    val link: String?=null
)