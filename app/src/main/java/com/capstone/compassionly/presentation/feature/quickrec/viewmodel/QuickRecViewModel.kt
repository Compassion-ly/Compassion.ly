package com.capstone.compassionly.presentation.feature.quickrec.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.compassionly.repository.core.network.QuickRecRepository

class QuickRecViewModel(val quickRecRepository: QuickRecRepository) : ViewModel(){

    fun sendUserDesc(token : String, text: String) = quickRecRepository.sendUserDesc(token, text)
}