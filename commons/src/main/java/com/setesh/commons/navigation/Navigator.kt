package com.setesh.commons.navigation

import androidx.navigation.NavDirections
import com.setesh.commons.navigation.dialog.DialogData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

open class Navigator {

    private val _directions = MutableSharedFlow<Route>(extraBufferCapacity = 1)
    val directions: SharedFlow<Route> = _directions

    open fun goTo(directions: NavDirections) {
        _directions.tryEmit(Route.Forward(directions))
    }

    open fun openDialog(data: DialogData) {
        _directions.tryEmit(Route.Dialog(data))
    }

    open fun back() {
        _directions.tryEmit(Route.Back)
    }
}
