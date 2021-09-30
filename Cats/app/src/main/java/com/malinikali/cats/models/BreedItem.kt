package com.malinikali.cats.models

import com.google.gson.annotations.SerializedName


data class BreedItem(
    val breed: String,
    val coat: String,
    val country: String,
    val origin: String,
    val pattern: String
)