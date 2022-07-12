package com.meli.android.search.presentation

import androidx.lifecycle.ViewModel
import com.meli.android.search.data.OnlineSearchRepository
import com.meli.android.search.data.remote.SearchRemoteDataSource
import com.meli.android.search.data.remote.api.SearchApi
import com.meli.android.search.data.source.RemoteDataSource
import com.meli.android.search.domain.repository.SearchRepository
import com.meli.android.search.domain.usecase.GetProductsByCategoryUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Anibal Cortez on 12-07-22.
 */
class SearchViewModel : ViewModel() {

    companion object {
        private const val API_BASE_URL = "https://api.mercadolibre.com/"
        val searchApi: SearchApi = Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }
    private val remoteDataSource: RemoteDataSource = SearchRemoteDataSource(searchApi)
    private val repository: SearchRepository = OnlineSearchRepository(remoteDataSource)
    private val getProductsByCategory = GetProductsByCategoryUseCase(repository)

}
