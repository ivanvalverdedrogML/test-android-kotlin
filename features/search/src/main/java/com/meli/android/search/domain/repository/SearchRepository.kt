package com.meli.android.search.domain.repository

import com.meli.android.search.domain.model.SearchItem

/**
 * Created by Anibal Cortez on 12-07-22.
 */
interface SearchRepository {
   suspend fun getProductsByCategory(categoryId : String) : List<SearchItem>
}
