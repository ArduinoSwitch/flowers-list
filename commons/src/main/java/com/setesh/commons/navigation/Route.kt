package com.setesh.commons.navigation

import androidx.navigation.NavDirections
import com.setesh.commons.navigation.dialog.DialogData

sealed class Route {
    open class Forward(val direction: NavDirections) : Route()
    open class Dialog(val data: DialogData) : Route()
    object Back : Route()
}
