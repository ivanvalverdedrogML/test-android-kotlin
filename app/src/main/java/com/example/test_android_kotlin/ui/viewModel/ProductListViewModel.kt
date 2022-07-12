package com.example.test_android_kotlin.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_android_kotlin.data.model.Model
import com.example.test_android_kotlin.data.model.response.NetworkStatus
import com.example.test_android_kotlin.data.repository.products.datasource.ProductDataSource
import com.example.test_android_kotlin.data.repository.products.datasource.ProductRemoteDataSource
import com.example.test_android_kotlin.domain.repository.product.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductListViewModel: ViewModel() {

    val liveListProduct = MutableLiveData<Array<Model.ProductListResponseItem2>>()
    val networkState = MutableLiveData<NetworkStatus>()
    val productIdsList = MutableLiveData<String>()

    private val repository = ProductRepository(
        productDataSource = ProductDataSource(
            ProductRemoteDataSource()
        )
    )

    /**
     * Get API products by ids
     */
    fun getProductsIds(category: String, offSet: Int) {
        networkState.postValue(NetworkStatus.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
            val call = repository.getProductListWithOffset(category, offSet)
            if (call.isSuccessful) {
                var ids = ""
                call.body()?.let {
                    for(product in it.results){
                        ids = ids + product.id + ","
                    }
                }
                productIdsList.postValue(ids.substring(0, ids.length-1))
                networkState.postValue(NetworkStatus.SUCCESS)

            }else{
                networkState.postValue(NetworkStatus.ERROR)
            }
        }
    }

    fun getProducts(ids: String) {
        networkState.postValue(NetworkStatus.LOADING)
        CoroutineScope(Dispatchers.IO).launch {
            val call = repository.getProductList(ids)
            if (call.isSuccessful) {
                liveListProduct.postValue(call.body())
                networkState.postValue(NetworkStatus.SUCCESS)

            }else{
                networkState.postValue(NetworkStatus.ERROR)
            }
        }
    }
}
