package com.example.dobrazil.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dobrazil.data.LocalStorage

/**
 * @brief ViewModel for LocalStorage
 */
class LocalStorageViewModel : ViewModel() {
    private val _localStorage = MutableLiveData(LocalStorage(""))
    val localStorage: LiveData<LocalStorage> = _localStorage

    /**
     * @brief Get the username
     */
    fun getUsername(): String {
        return _localStorage.value?.username ?: ""
    }

    /**
     * @brief Set the username
     */
    fun setUsername(username: String) {
        _localStorage.value?.username = username
    }
}