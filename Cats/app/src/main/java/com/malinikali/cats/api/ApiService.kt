package com.malinikali.cats.api

import com.malinikali.cats.helper.Constants
import com.malinikali.cats.models.BreedItem
import com.malinikali.cats.models.BreedResponse
import com.malinikali.cats.models.FactItem
import com.malinikali.cats.models.FactsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET(Constants.BREEDS)
    suspend fun getListOfBreeds(@Query("page") page: Int):Response<BreedResponse>

    @GET(Constants.FACT)
    suspend fun getRandomFact():Response<FactItem>

    @GET(Constants.FACTS)
    suspend fun getListOfFacts(@Query("page") page: Int):Response<FactsResponse>
}