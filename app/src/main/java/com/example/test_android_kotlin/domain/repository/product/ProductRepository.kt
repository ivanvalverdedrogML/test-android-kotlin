package com.example.test_android_kotlin.domain.repository.product

import com.example.test_android_kotlin.data.model.Model
import com.example.test_android_kotlin.data.model.response.Resource
import com.example.test_android_kotlin.data.repository.products.datasource.ProductDataSource
import com.example.test_android_kotlin.data.repository.products.datasource.ProductRemoteDataSource
import retrofit2.Response

class ProductRepository constructor(private val productDataSource: ProductDataSource) {

    private val productRemoteDataSource = ProductRemoteDataSource()

    suspend fun getProductList(category: String): Response<Array<Model.ProductListResponseItem2>> {
        return productDataSource.getProductList(category)
    }

    suspend fun getProductListWithOffset(category: String, offSet: Int): Response<Model.ProductListResponseItem> {
        return productDataSource.getProductListWithOffset(category, offSet)
    }

    suspend fun getCategories(query: String): Resource<String> = productRemoteDataSource.getCategories(query)

}
