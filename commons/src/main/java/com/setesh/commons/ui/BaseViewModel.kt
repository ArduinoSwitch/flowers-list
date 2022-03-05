package com.setesh.commons.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setesh.commons.di.FrontDispatchers
import kotlinx.coroutines.plus

open class BaseViewModel(dispatchers: FrontDispatchers) : ViewModel() {
    val scope = viewModelScope + dispatchers.ui
}
