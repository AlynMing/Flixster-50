package com.example.flixster.models

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

}