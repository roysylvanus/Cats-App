package com.malinikali.cats.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.malinikali.cats.api.ApiService
import com.malinikali.cats.models.BreedItem

class BreedPagingDataSource(private val apiService: ApiService):PagingSource<Int,BreedItem>() {
    override fun getRefreshKey(state: PagingState<Int, BreedItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BreedItem> {
       return try {
            val  currentPage = params.key ?: 1
           val response = apiService.getListOfBreeds(currentPage)
            val data = response.body()?.results ?: emptyList()
           val  responseData = mutableListOf<BreedItem>()
           responseData.addAll(data)

           LoadResult.Page(
               data = responseData,
               prevKey = if (currentPage == 1) null else -1,
               nextKey = currentPage.plus(1)
           )
       }
       catch (e: Exception){
            LoadResult.Error(e)
       }
    }
}