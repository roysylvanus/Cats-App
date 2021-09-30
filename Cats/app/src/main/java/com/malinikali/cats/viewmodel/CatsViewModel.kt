package com.malinikali.cats.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.malinikali.cats.api.ApiService
import com.malinikali.cats.models.FactItem
import com.malinikali.cats.paging.BreedPagingDataSource
import com.malinikali.cats.paging.FactPagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel
@Inject
constructor(private val apiService: ApiService): ViewModel() {


    val breedListData = Pager(PagingConfig(pageSize = 1)){
        BreedPagingDataSource(apiService)
    }.flow.cachedIn(viewModelScope)

    val factListData = Pager(PagingConfig(pageSize = 1)){
        FactPagingDataSource(apiService)
    }.flow.cachedIn(viewModelScope)




}
