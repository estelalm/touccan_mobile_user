package br.senai.sp.jandira.touccanuser

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    companion object {


        private val ID_KEY = intPreferencesKey("user_id")
    }

    val userId: Flow<Int?> = dataStore.data
        .map { preferences -> preferences[ID_KEY] }

    suspend fun saveUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[ID_KEY] = userId
        }
    }
}