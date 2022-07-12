package com.example.test_android_kotlin.data.model.response

data class Resource<out T>(
    val status: NetworkStatus,
    val data: T? = null,
    val message: String? = null
)
