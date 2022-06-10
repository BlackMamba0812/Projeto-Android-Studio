package com.example.myapplication.model

class Categoria(
    var id: Long,
    var descrição: String,
    var tarefas: List<Tarefa>
) {
}