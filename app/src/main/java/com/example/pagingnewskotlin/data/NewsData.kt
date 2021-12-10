package com.example.pagingnewskotlin.data

import androidx.paging.PageKeyedDataSource
import com.example.pagingnewskotlin.model.ArticlesItem
import com.example.pagingnewskotlin.network.NewsService
import com.example.pagingnewskotlin.network.ResApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsData: PageKeyedDataSource<Long, ArticlesItem>() {

    private var api: NewsService? = null

    init {
        api = ResApi.restApi()
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, ArticlesItem>) {
        api?.getNews("", "d365302acf244531b773ab7075bf271d", 1, params.requestedLoadSize)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ t ->
                t.articles?.let { callback.onResult(it, null, 2L) }
            }, {})

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ArticlesItem>) {
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ArticlesItem>) {
        api?.getNews("", "d365302acf244531b773ab7075bf271d", params.key, params.requestedLoadSize)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ t ->
                t.articles?.let { callback.onResult(it, params.key + 1L) }
            }, {})
    }

}