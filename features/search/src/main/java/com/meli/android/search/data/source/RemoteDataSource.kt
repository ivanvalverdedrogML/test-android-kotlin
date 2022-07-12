package com.meli.android.search.data.source

import com.meli.android.search.data.remote.model.RemoteSearchItem

/**
 * Created by Anibal Cortez on 12-07-22.
 */
interface RemoteDataSource {
    fun getProductByCategory(categoryId :String) : List<RemoteSearchItem>
}
