package com.example.flixster

import com.google.gson.annotations.SerializedName

class Show {
    @SerializedName("name")
    var title: String? = null

    @SerializedName("poster_path")
    var showImageUrl: String? = null

    @SerializedName("overview")
    var description: String? = null
}