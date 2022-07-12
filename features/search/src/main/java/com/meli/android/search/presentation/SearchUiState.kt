package com.meli.android.search.presentation

import com.meli.android.search.domain.model.SearchItem

/**
 * Created by Anibal Cortez on 12-07-22.
 */
data class SearchUiState(
    var loading: Boolean = false,
    var productList: List<SearchItem>? = null,
    val error: DataError? = null
)

sealed class DataError {
    object Network : DataError()
    data class ServerError(val code: Int) : DataError()
    object Undefined : DataError()
}
