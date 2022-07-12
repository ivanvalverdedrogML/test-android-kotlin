package com.example.test_android_kotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

object Model {

    data class ProductListResponseItem(
        val results: Array<Product>
    )

    data class ProductListResponseItem2(
        val body: Product
    )

    class Product(
        val id: String,
        val price: Double,
        val title: String,
        val pictures: List<Picture>

    ) : Serializable

    class Picture(
        val secure_url: String
    )

    data class Category(@SerializedName("domain_id")
                        val domainId:String = "",
                        @SerializedName("domain_name")
                        val domainName:String = "",
                        @SerializedName("category_id")
                        val categoryId:String = "",
                        @SerializedName("category_name")
                        val categoryName:String = "")
}
