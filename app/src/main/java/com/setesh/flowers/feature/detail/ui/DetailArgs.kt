package com.setesh.flowers.feature.detail.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailArgs(
    val description: String,
    val width: Long,
    val height: Long,
    val likes: Long,
    val fullUrl: String,
): Parcelable
