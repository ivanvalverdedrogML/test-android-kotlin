package com.example.test_android_kotlin.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_android_kotlin.data.repository.products.datasource.ProductDataSource
import com.example.test_android_kotlin.data.repository.products.datasource.ProductRemoteDataSource
import com.example.test_android_kotlin.domain.repository.product.ProductRepository

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = ProductRepository(
            productDataSource = ProductDataSource(
                ProductRemoteDataSource()
            )
        )
        return SearchViewModel(repository) as T
    }
}
