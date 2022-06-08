package com.example.myapplication

import android.os.Bundle
import android.service.controls.actions.FloatAction
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.TarefaAdapter
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(layoutInflater, container,false)


        val listTarefas = listOf(
            Tarefa(
                "Lavar o carro",
            "Higienizar os bancos",
            "Lucas",
            "2022-06-11",
            false,
            "Tarefa"
            ),
            Tarefa(
                "Jogar Basquete",
                "Jogar na quadra do Maia",
                "Lucas",
                "2022-06-11",
                false,
                "Lazer"
        ),
            Tarefa(
                "Cortar o Cabelo",
                "Fazer o corte nudread",
                "Lucas",
                "2022-06-12",
                false,
                "Moda"
               )
            )

        //Configuração da RecyclerView

        val adapter =TarefaAdapter()
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        adapter.setList(listTarefas)


        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        return binding.root
    }
}


