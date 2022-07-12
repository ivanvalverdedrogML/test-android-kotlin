package com.example.test_android_kotlin.data.repository.products.datasource

import com.example.test_android_kotlin.data.model.Model
import retrofit2.Response

class ProductDataSource(private val productRemoteDataSource: ProductRemoteDataSource) {

    suspend fun getProductList(category: String): Response<Array<Model.ProductListResponseItem2>> {
        return productRemoteDataSource.getProductList(category)
    }

    suspend fun getProductListWithOffset(category: String, offSet: Int): Response<Model.ProductListResponseItem> {
        return productRemoteDataSource.getProductListWithOffset(category, offSet)
    }

}
