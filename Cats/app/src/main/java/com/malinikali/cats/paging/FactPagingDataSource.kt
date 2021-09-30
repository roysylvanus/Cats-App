package com.malinikali.cats.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.malinikali.cats.api.ApiService
import com.malinikali.cats.models.FactItem

class FactPagingDataSource(private val apiService: ApiService):PagingSource<Int,FactItem>() {
    override fun getRefreshKey(state: PagingState<Int, FactItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FactItem> {
        return try {
            val  currentPage = params.key ?: 1
            val response = apiService.getListOfFacts(currentPage)
            val data = response.body()?.results ?: emptyList()
            val  responseData = mutableListOf<FactItem>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}