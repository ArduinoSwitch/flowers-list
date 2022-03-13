package com.setesh.commons.utils

import java.text.SimpleDateFormat
import java.util.*

private const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:SS"

fun String.dateTimeToMiliseconds() =
    SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).parse(this)?.time ?: 0L

fun Long.toDateFormatString() =
    SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(Date(this))
