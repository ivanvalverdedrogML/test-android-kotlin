package com.meli.android.search.data

import com.meli.android.search.data.source.RemoteDataSource
import com.meli.android.search.data.mappers.asDomain
import com.meli.android.search.domain.model.SearchItem
import com.meli.android.search.domain.repository.SearchRepository

/**
 * Created by Anibal Cortez on 12-07-22.
 */
class OnlineSearchRepository(
    private val remoteDatasource: RemoteDataSource
) : SearchRepository {

    override suspend fun getProductsByCategory(categoryId: String): List<SearchItem> =
        remoteDatasource.getProductByCategory(categoryId)
            .map { remoteSearchItem ->
                remoteSearchItem.asDomain()
            }
}
