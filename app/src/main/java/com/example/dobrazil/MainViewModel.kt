package com.example.dobrazil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Entity.ProfilEntity
import com.example.dobrazil.EntityRepositories.ProfilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject public constructor(
    private val repository: ProfilRepository
) : ViewModel(){
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    fun insert(event: ProfilEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }

    fun delete(event: ProfilEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(event)
        }
    }

    fun update(event: ProfilEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(event)
        }
    }
}