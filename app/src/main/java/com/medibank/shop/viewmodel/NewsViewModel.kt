package com.medibank.shop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.medibank.shop.base.BaseNewsViewModel
import com.medibank.shop.model.SourcedsData
import com.medibank.shop.usecase.INewsUseCase
import com.medibank.shop.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: INewsUseCase) :
    BaseNewsViewModel(), SelectedData{

    private val mNewsList = MutableLiveData<List<SourcedsData>>()
    val newsList: LiveData<List<SourcedsData>>
        get() = mNewsList

    private val mAllNewsList = MutableLiveData<List<SourcedsData>>()
    val allNewsList: LiveData<List<SourcedsData>>
        get() = mAllNewsList

    /**
     * Get all news
     *
     */
    fun refreshAllNews() = processUseCase {
        mAllNewsList.value = useCase.getSources()
    }

    val eventURLSelected = SingleLiveEvent<String>()

    /**
     * Get news depending on source id selected by calling getNews(sourceId) to get list
     *
     * @param sourceId : Source parameter Id
     */
    fun refreshNews(sourceId: String) = processUseCase {
        mNewsList.value = useCase.getNews(sourceId)
    }

    //To get selected row to display only those news
    override fun onSelectData( url: String) {
        eventURLSelected.value = url
    }
}

interface SelectedData {
    fun onSelectData(url: String)
}
