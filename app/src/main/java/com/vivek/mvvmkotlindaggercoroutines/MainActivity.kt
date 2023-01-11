package com.vivek.mvvmkotlindaggercoroutines

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivek.mvvmkotlindaggercoroutines.data.model.Article
import com.vivek.mvvmkotlindaggercoroutines.databinding.ActivityMainBinding
import com.vivek.mvvmkotlindaggercoroutines.di.component.DaggerActivityComponent
import com.vivek.mvvmkotlindaggercoroutines.di.module.ActivityModule
import com.vivek.mvvmkotlindaggercoroutines.ui.adapter.TopHeadlineAdapter
import com.vivek.mvvmkotlindaggercoroutines.ui.viewmodels.TopHeadlineViewModel
import com.vivek.mvvmkotlindaggercoroutines.utils.Status
import com.vivek.mvvmkotlindaggercoroutines.utils.Utility
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var topHeadlineViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utility.makeFullScreen(this)
        supportActionBar?.hide()
        setupUI()
        setupObserver()

    }

    private fun setupObserver() {
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.articleList.collect {

                    when (it.status) {

                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { articleList -> renderList(articleList) }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        Status.ERROR -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(articleList: List<Article>) {

        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun setupUI() {

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun injectDependencies() {

        DaggerActivityComponent
            .builder()
            .applicationComponent((application as App).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }
}