package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.TarefaAdapter
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.util.MainViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(layoutInflater, container,false)

        mainViewModel.listTarefa()




        //Configuração da RecyclerView

        val adapter =TarefaAdapter()
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)



        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
            response -> if (response.body() != null){
                adapter.setList(response.body()!!)
            }
        }

        return binding.root
    }
}


