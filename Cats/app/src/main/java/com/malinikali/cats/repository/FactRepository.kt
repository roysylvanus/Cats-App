package com.malinikali.cats.repository

import com.malinikali.cats.api.ApiService
import javax.inject.Inject


class FactRepository
@Inject constructor(private val apiService: ApiService){

    suspend fun getRandomFact() = apiService.getRandomFact()
}