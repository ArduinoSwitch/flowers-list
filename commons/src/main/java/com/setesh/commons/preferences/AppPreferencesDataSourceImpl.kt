package com.setesh.commons.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.setesh.commons.di.BackDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

private const val ON_BOARD_STATE = "on_board_state"

class AppPreferencesDataSourceImpl(
    private val dispatchers: BackDispatchers,
    private val dataStore: DataStore<Preferences>,
): AppPreferencesDataSource {
    override suspend fun getOnBoardStatus(): Boolean =
        dataStore.get(ON_BOARD_STATE, false).first()

    override suspend fun updateOnBoardStatus(): Unit = withContext(dispatchers.io) {
        dataStore.set(ON_BOARD_STATE, true)
    }

    companion object {
        const val PREF_NAME = "app_preferences"
    }
}

private inline fun <reified T : Any> DataStore<Preferences>.get(
    key: String,
    defaultValue: T,
): Flow<T> = data.catch { exception ->
    if (exception is IOException) emit(emptyPreferences())
    else throw exception
}.map { preferences ->
    preferences[preferencesKey<T>(key)] ?: defaultValue
}

private suspend inline fun <reified T : Any> DataStore<Preferences>.set(
    key: String,
    value: T?,
): Preferences = edit { preferences ->
    if (value == null) preferences.remove(preferencesKey<Int>(key))
    else preferences[preferencesKey<T>(key)] = value
}
