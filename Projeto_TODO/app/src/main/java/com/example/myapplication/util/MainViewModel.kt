package com.example.myapplication.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Repository
import com.example.myapplication.model.Categoria
import com.example.myapplication.model.Tarefa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var tarefaSelecionada: Tarefa? = null

    private val _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()

    val myCategoriaResponse: LiveData<Response<List<Categoria>>> = _myCategoriaResponse

    private val _myTarefaResponse =
        MutableLiveData<Response<List<Tarefa>>>()

    val myListTarefa: LiveData<Response<List<Tarefa>>> = _myTarefaResponse

    val dataSelecionada = MutableLiveData<LocalDate>()

    init {
        /*não será preciso utlizar esse método agora, ele só será utilizado quando o FormFragment
        for acessado

         */
        //listCategoria()

    }

    fun listCategoria() {
        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _myCategoriaResponse.value = response
            } catch (e: Exception) {
                Log.d("ERRO", e.message.toString())
            }

        }

    }

    fun addTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                repository.addTarefa(tarefa)
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listTarefa(){
        viewModelScope.launch {
            try {
                val response = repository.listTarefa()
                _myTarefaResponse.value =response
            } catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }

        fun updateTrefa(tarefa: Tarefa){
            viewModelScope.launch {
                try {
                    repository.updateTarefa(tarefa)
                }catch (e: Exception){
                    Log.d("Erro", e.message.toString())
                }
            }
        }
    }
}