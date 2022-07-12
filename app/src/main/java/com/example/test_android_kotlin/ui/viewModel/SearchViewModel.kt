package com.example.test_android_kotlin.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android_kotlin.data.model.response.NetworkStatus
import com.example.test_android_kotlin.data.model.response.Resource
import com.example.test_android_kotlin.domain.repository.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel constructor(private val repository: ProductRepository) : ViewModel() {
        private val _category = MutableLiveData<Resource<String>>()

        val category: LiveData<Resource<String>>
            get() = _category

        fun getCategories(category: String){
            viewModelScope.launch {
                _category.value = Resource(NetworkStatus.LOADING)
                withContext(Dispatchers.IO){
                    repository.getCategories(category)
                }.also { _category.value = it }
            }
        }
}
