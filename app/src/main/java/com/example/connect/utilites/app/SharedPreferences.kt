@file:Suppress("UNCHECKED_CAST")

package com.example.connect.utilites.app

import android.content.Context
import android.os.Build.ID

class SharedPreferences(private val context: Context) {
    companion object {
        private const val PREF = "Connect"
        private const val ROLE_ID = "roleId"
        private const val PREF_TOKEN = "user_token"
        private const val USERID = "user_id"
    }

    private val sharedPreference = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveTokenRoleId(token: String, roleId: Int) {
        put(PREF_TOKEN, token)
        put(ROLE_ID, roleId)
    }

    fun saveToken(token: String, roleId: Int, userId: Int) {
        put(PREF_TOKEN, token)
        put(ROLE_ID, roleId)
        put(USERID, userId)
    }

    fun getToken() = get(PREF_TOKEN, String::class.java)

    fun getRoleId() = get(ROLE_ID, Int::class.java)

    fun getIdUser() = get(USERID, Int::class.java)

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPreference.getString(key, "")
            Boolean::class.java -> sharedPreference.getBoolean(key, false)
            Float::class.java -> sharedPreference.getFloat(key, -1f)
            Double::class.java -> sharedPreference.getFloat(key, -1f)
            Int::class.java -> sharedPreference.getInt(key, -1)
            Long::class.java -> sharedPreference.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPreference.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPreference.edit().run {
            remove(PREF_TOKEN)
            remove(ROLE_ID)
            remove(USERID)
        }.apply()
    }
}