package com.meli.android.search.domain.usecase

import com.meli.android.search.domain.model.SearchItem
import com.meli.android.search.domain.repository.SearchRepository

/**
 * Created by Anibal Cortez on 12-07-22.
 */
class GetProductsByCategoryUseCase(private val repository: SearchRepository) {
   suspend operator fun invoke(categoryId: String): List<SearchItem> =
        repository.getProductsByCategory(categoryId)
}
