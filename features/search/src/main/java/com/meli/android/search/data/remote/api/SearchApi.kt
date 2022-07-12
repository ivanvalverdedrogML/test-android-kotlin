package com.meli.android.search.data.remote.api

import com.meli.android.search.data.remote.model.RemoteSearchItem
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anibal Cortez on 12-07-22.
 */
interface SearchApi {
    @GET("items")
    suspend fun getProductsByCategory(@Query("ids") categoryId: String): List<RemoteSearchItem>
}
