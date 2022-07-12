package com.example.test_android_kotlin.data.repository.products.datasource

import com.example.test_android_kotlin.BuildConfig
import com.example.test_android_kotlin.data.model.Model
import com.example.test_android_kotlin.data.model.RetrofitServices
import com.example.test_android_kotlin.data.model.response.NetworkStatus
import com.example.test_android_kotlin.data.model.response.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class ProductRemoteDataSource {

    suspend fun getProductList(ids: String): Response<Array<Model.ProductListResponseItem2>>{
        return RetrofitServices.instance.getProductList("items?ids=" + ids)
    }

    suspend fun getProductListWithOffset(category: String, offSet: Int): Response<Model.ProductListResponseItem> {
        return RetrofitServices.instance.getProductListWithOffset("sites/MLA/search?category=" + category + "&offset=" + offSet + "&limit=20")
    }

    suspend fun getCategories(searchTerm: String): Resource<String> {
        return try {
            val response = RetrofitServices.instance.getCategories(searchTerm = searchTerm)
            Resource(NetworkStatus.SUCCESS, response.body()?.first()?.categoryId ?: "")
        } catch (e: Exception) {
            Resource(NetworkStatus.ERROR, "")
        }
    }

}
