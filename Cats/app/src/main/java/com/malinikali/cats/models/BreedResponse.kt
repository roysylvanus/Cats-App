package com.malinikali.cats.models


import com.google.gson.annotations.SerializedName

data class BreedResponse(
    val current_Page:Int,
    val results: ArrayList<BreedItem>,


)