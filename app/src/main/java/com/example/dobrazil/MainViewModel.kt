package com.example.dobrazil

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dobrazil.Dao.EventDao
import com.example.dobrazil.Entity.EventEntity
import com.example.dobrazil.EntityRepositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject public constructor(
    private val repository: EventRepository
) : ViewModel(){

    init {
        getAll()
    }

    fun getAll(){
        viewModelScope.launch {
            repository.getAll()
        }
    }

    fun insert(event: EventEntity){
        viewModelScope.launch {
            repository.insert(event)
        }
    }

    fun delete(event: EventEntity){
        viewModelScope.launch {
            repository.delete(event)
        }
    }

    fun update(event: EventEntity){
        viewModelScope.launch {
            repository.update(event)
        }
    }
}