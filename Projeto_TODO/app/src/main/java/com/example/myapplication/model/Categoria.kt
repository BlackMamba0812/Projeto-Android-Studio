package com.example.myapplication.model

data class Categoria(
    var id: Long,
    var descricao: String?,
    var tarefas: List<Tarefa>?
    /* Delegamos as variaveis como nula, para definir quando contruimos um objeto do tipo Categoria,
    para podermos a partir desse momento indicar ele apenas com o ID. assim conseguiremos salvar os
    dados dentro do banco de maneira correta.
     */

    ) {

    override fun toString(): String {
        return descricao!!
        //"!!" especificando que a descrição não será nulo.
    }
}