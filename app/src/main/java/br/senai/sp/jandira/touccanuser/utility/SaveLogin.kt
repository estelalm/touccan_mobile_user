package br.senai.sp.jandira.touccanuser.utility

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserPreferencesKeys {
    val ID = stringPreferencesKey("username")
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
}

suspend fun saveLogin(context: Context, username: String) {
    context.dataStore.edit { preferences ->
        preferences[UserPreferencesKeys.ID] = username
        preferences[UserPreferencesKeys.IS_LOGGED_IN] = true
    }
}

fun getLogin(context: Context): Flow<Pair<String?, Boolean>> {
    return context.dataStore.data.map { preferences ->
        val username = preferences[UserPreferencesKeys.ID]
        val isLoggedIn = preferences[UserPreferencesKeys.IS_LOGGED_IN] ?: false
        Pair(username, isLoggedIn)
    }
}
