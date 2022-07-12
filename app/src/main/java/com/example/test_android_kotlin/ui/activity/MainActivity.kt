package com.example.test_android_kotlin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.test_android_kotlin.R
import com.example.test_android_kotlin.data.model.response.NetworkStatus
import com.example.test_android_kotlin.data.model.response.Resource
import com.example.test_android_kotlin.databinding.ActivityMainBinding
import com.example.test_android_kotlin.ui.viewModel.SearchViewModel
import com.example.test_android_kotlin.ui.viewModel.SearchViewModelFactory
import com.example.test_android_kotlin.util.Constants

class MainActivity : AppCompatActivity(), android.widget.SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SearchViewModel by viewModels(factoryProducer = { SearchViewModelFactory() })

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setObserver()
        binding.svQuery.setOnQueryTextListener(this)
    }

    private fun setObserver() {
        viewModel.category.observe(this@MainActivity, Observer(::getCategories))
    }

    private fun getCategories(categoryResource: Resource<String>){
        when (categoryResource.status){
            NetworkStatus.SUCCESS -> {
                categoryResource.data?.let {
                    navigateToProductListActivity(it)
                }
            }
            NetworkStatus.ERROR -> {
                navigateToProductListActivity("")
            }
            else -> {
                Log.ERROR
            }
        }
    }
    private fun navigateToProductListActivity(category: String){
        val intent = Intent(this, ProductListActivity::class.java)
        intent.putExtra(Constants.CATEGORY_ID_KEY, category)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let{
            viewModel.getCategories(it)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}
