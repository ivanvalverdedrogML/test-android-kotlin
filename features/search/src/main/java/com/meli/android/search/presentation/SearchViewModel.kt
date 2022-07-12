package com.meli.android.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meli.android.search.data.OnlineSearchRepository
import com.meli.android.search.data.remote.SearchRemoteDataSource
import com.meli.android.search.data.remote.api.SearchApi
import com.meli.android.search.data.source.RemoteDataSource
import com.meli.android.search.domain.model.SearchItem
import com.meli.android.search.domain.repository.SearchRepository
import com.meli.android.search.domain.usecase.GetProductsByCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
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
    private val useCase = GetProductsByCategoryUseCase(repository)


    private val _uiState = MutableStateFlow(SearchUiState())
    private val uiState: StateFlow<SearchUiState> = _uiState

    private var job: Job? = null

    fun getProductsByCategory(categoryId: String) {
        if (_uiState.value.productList == null) {
            job?.cancel()
            job = viewModelScope.launch(Dispatchers.Default) {
                updateUiState(loading = true)
                getProducts(categoryId)
            }
        }
    }

    private suspend fun getProducts(categoryId: String) {
        try {
            updateUiState(productList = useCase(categoryId))
        } catch (e: Exception) {
            updateUiState(error = handlerException(e))
        }
    }

    private fun handlerException(exception: Exception): DataError = when (exception) {
        is HttpException -> DataError.ServerError(exception.code())
        else -> DataError.Undefined
    }

    private fun updateUiState(
        loading: Boolean = false,
        error: DataError? = null,
        productList: List<SearchItem>? = null
    ) {
        _uiState.value = _uiState.value.copy(
            loading = loading, productList = productList, error = error
        )
    }
}
