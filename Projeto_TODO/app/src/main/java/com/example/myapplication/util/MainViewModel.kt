package com.example.myapplication.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Repository
import com.example.myapplication.model.Categoria
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val repository = Repository()

    private val _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()

    val myCategoriaResponse: LiveData<Response<List<Categoria>>> = _myCategoriaResponse

    fun lisCategoria(){

        viewModelScope.launch {
            _myCategoriaResponse.value = repository.listCategoria()
        }
    }
}