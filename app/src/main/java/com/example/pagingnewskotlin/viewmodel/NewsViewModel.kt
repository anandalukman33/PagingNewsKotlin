package com.example.pagingnewskotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingnewskotlin.data.factory.NewsDataFactory
import com.example.pagingnewskotlin.model.ArticlesItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class NewsViewModel: ViewModel() {
    private var executor: Executor
    private var articleData: LiveData<PagedList<ArticlesItem>>

    init {
        executor = Executors.newFixedThreadPool(5)

        var newsFactory = NewsDataFactory()

        var pageListConfig = PagedList.Config.Builder()
            .setPageSize(20) // setup ukuran lembar saat load data dari API
            .setInitialLoadSizeHint(10) // inisial pertama kali saat load data itu dibuat 10
            .setEnablePlaceholders(false) // pada saat dia loading dia menampilkan apa?
            .build()

        articleData = LivePagedListBuilder(newsFactory, pageListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun getArticle(): LiveData<PagedList<ArticlesItem>> {
        return articleData
    }

}