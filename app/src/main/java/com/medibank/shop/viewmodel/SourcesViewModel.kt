package com.medibank.shop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.medibank.shop.base.BaseNewsViewModel
import com.medibank.shop.model.SourcedsData
import com.medibank.shop.usecase.INewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val useCase: INewsUseCase) :
    BaseNewsViewModel(){

    private val mSourcesList = MutableLiveData<List<SourcedsData>>()
    val sourcesList: LiveData<List<SourcedsData>>
        get() = mSourcesList

    fun refreshSources() = processUseCase {
        mSourcesList.value = useCase.getSources()
    }

}
