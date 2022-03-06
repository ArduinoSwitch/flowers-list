package com.setesh.commons.preferences

interface AppPreferencesDataSource {
    suspend fun getOnBoardStatus(): Boolean
    suspend fun updateOnBoardStatus()
}