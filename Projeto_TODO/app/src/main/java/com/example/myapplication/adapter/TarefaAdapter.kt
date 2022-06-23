package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CardLayoutBinding
import com.example.myapplication.model.Categoria
import com.example.myapplication.model.Tarefa
import com.example.myapplication.util.MainViewModel

class TarefaAdapter(
    val taskClickListener: TaskClickListener,
    val mainViewModel: MainViewModel

         ) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

   private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder (val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
       val tarefa = listTarefa[position]
        holder.binding.textNome.text = tarefa.nome
        holder.binding.textDescriO.text = tarefa.descricao
        holder.binding.textResponsVel.text = tarefa.responsavel
        holder.binding.textData.text = tarefa.data
        holder.binding.switchAtivo.isChecked = tarefa.status
        holder.binding.textCategoria.text = tarefa.categoria.descricao

        holder.itemView.setOnClickListener{
            taskClickListener.onTaskClickListener(tarefa)
        }
    }

    override fun getItemCount(): Int {
        return listTarefa.size

    }

    fun setList (list: List<Tarefa>){
        listTarefa = listTarefa
        notifyDataSetChanged()
    }
}