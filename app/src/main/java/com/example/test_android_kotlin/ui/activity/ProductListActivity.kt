package com.example.test_android_kotlin.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_android_kotlin.data.model.Model
import com.example.test_android_kotlin.data.model.response.NetworkStatus
import com.example.test_android_kotlin.databinding.ActivityProductListBinding
import com.example.test_android_kotlin.ui.adapter.ProductAdapter
import com.example.test_android_kotlin.ui.viewModel.ProductListViewModel
import com.example.test_android_kotlin.util.Constants.CATEGORY_ID_KEY

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    private var categoryId = ""
    private var page = 1
    private var isFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        categoryId = intent.getStringExtra(CATEGORY_ID_KEY).toString()
        initView()
        setViewModel()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        if (this::productAdapter.isInitialized) {
            productAdapter.notifyDataSetChanged()
        }
    }

    private fun initView() {
        binding.productsList.apply {
            productAdapter = ProductAdapter(arrayListOf())
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        page += 20
                        viewModel.getProductsIds(categoryId, page)
                    }
                }
            })
        }
    }

    private fun setViewModel() {
        with(viewModel) {
            liveListProduct.observe(this@ProductListActivity) { it ->
                val productList = mutableListOf<Model.Product>()
                for (products in it) {
                    productList.add(products.body)
                }
                productAdapter.updateList(productList as ArrayList<Model.Product>)
                isFirstTime = false
            }

            networkState.observe(this@ProductListActivity) {
                manageProductList(it)
            }

            productIdsList.observe(this@ProductListActivity) {
                viewModel.getProducts(it)
            }
        }

        viewModel.getProductsIds(categoryId, page)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun manageProductList(condition: NetworkStatus) {
        with(binding) {
            when (condition) {
                NetworkStatus.LOADING -> {
                    if (isFirstTime) {
                        productsList.visibility = View.GONE
                    }
                }
                NetworkStatus.SUCCESS -> {
                    productsList.visibility = View.VISIBLE
                }

                NetworkStatus.ERROR -> {
                    productsList.visibility = View.VISIBLE
                    productAdapter.notifyDataSetChanged()
                }
                else -> {
                    productsList.visibility = View.VISIBLE
                    productAdapter.notifyDataSetChanged()
                }
            }
        }
    }

}
