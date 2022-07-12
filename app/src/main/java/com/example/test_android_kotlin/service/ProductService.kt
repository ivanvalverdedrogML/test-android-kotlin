package com.example.test_android_kotlin.service

import com.example.test_android_kotlin.BuildConfig
import com.example.test_android_kotlin.data.model.Model
import com.example.test_android_kotlin.util.Constants.ACCESS_TOKEN
import retrofit2.Response
import retrofit2.http.*

interface ProductService {

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("sites/{COUNTRY_ID}/domain_discovery/search")
    suspend fun getCategories(@Path("COUNTRY_ID")siteId:String = "MLA",
                              @Query("limit") limit: Int = 1,
                              @Query("q") searchTerm:String
    ) : Response<ArrayList<Model.Category>>

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET
    suspend fun getProductList(@Url url: String): Response<Array<Model.ProductListResponseItem2>>

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET
    suspend fun getProductListWithOffset(@Url url: String): Response<Model.ProductListResponseItem>
}
