package com.capstone.compassionly.presentation.feature.topic_histories.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.models.local.LocalHistoryTopic
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.FieldRecRepository
import com.capstone.compassionly.repository.core.network.MajorRecRepository
import kotlinx.coroutines.launch

class TopicHistoryViewModel(
    private val localDataSource: LocalDataSource,
    private val majorRecRepository: MajorRecRepository,
    private val fieldRecRepository: FieldRecRepository
) : ViewModel() {

    private var _resultHistory : MutableLiveData<List<LocalHistoryTopic>> = MutableLiveData()
    val resultHistory : LiveData<List<LocalHistoryTopic>> = _resultHistory

    fun getHistoryTopic(lifecycleOwner: LifecycleOwner, userId: Int)  = viewModelScope.launch {
        localDataSource.getHistoryTopic(userId).observe(lifecycleOwner) {
            _resultHistory.postValue(it)
        }
    }

    fun getHistoryTopicBySearch(lifecycleOwner: LifecycleOwner, search: String) = viewModelScope.launch {
        localDataSource.getHistoryWithTopic(search).observe(lifecycleOwner) {
            _resultHistory.postValue(it)
        }
    }

    fun getMajorResult() = majorRecRepository.getMajorRecResult().asLiveData()

    fun getFieldResult() = fieldRecRepository.getFieldRecResult().asLiveData()


    fun getUser() = localDataSource.getUser()

}