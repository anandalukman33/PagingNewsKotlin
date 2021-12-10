package com.example.pagingnewskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingnewskotlin.adapter.NewsListAdapter
import com.example.pagingnewskotlin.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: NewsViewModel? = null
    private var recyclerNews: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init RecyclerView
        recyclerNews = findViewById(R.id.listNews) as RecyclerView

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel?.getArticle()?.observe(this, Observer{
            val adapter = NewsListAdapter()
            adapter.submitList(it)
            recyclerNews?.adapter = adapter

        })
    }
}