package com.example.myapplication.model

data class Tarefa(
    var id: Long,
    var nome: String,
    var descrição: String,
    var responsavel: String,
    var data: String,
    var status: Boolean,
    var categoria: Categoria
    ) {
}