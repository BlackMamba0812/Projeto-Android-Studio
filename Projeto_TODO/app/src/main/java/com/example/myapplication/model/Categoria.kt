package com.example.myapplication.model

data class Categoria(
    var id: Long,
    var descrição: String,
    var tarefas: List<Tarefa>
) {
}