package com.setesh.commons.utils

import java.text.SimpleDateFormat
import java.util.*

private const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:SS"

fun String.dateTimeToMilliseconds(): Long =
    SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).parse(this)?.time ?: 0L

fun Long.toDateFormatString(): String =
    SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(Date(this))
