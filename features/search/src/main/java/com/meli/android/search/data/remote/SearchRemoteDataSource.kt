package com.meli.android.search.data.remote

import com.meli.android.search.data.remote.api.SearchApi
import com.meli.android.search.data.source.RemoteDataSource
import com.meli.android.search.data.remote.model.RemoteSearchItem
import kotlin.coroutines.coroutineContext

/**
 * Created by Anibal Cortez on 12-07-22.
 */
class SearchRemoteDataSource(
    private val api: SearchApi
) : RemoteDataSource {

    override suspend fun getProductByCategory(categoryId: String): List<RemoteSearchItem> =
        api.getProductsByCategory(categoryId)

}
