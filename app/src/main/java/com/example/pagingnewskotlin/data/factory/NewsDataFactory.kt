package com.example.pagingnewskotlin.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingnewskotlin.data.NewsData
import com.example.pagingnewskotlin.model.ArticlesItem

class NewsDataFactory: DataSource.Factory<Long, ArticlesItem>() {

    private var listOfDataLive: MutableLiveData<NewsData>
    private var dataNews: NewsData

    init {
        listOfDataLive = MutableLiveData()
        dataNews = NewsData()
    }

    override fun create(): DataSource<Long, ArticlesItem> {
        listOfDataLive.postValue(dataNews)
        return dataNews
    }

    fun getMutableLiveData(): MutableLiveData<NewsData> {
        return listOfDataLive
    }
}