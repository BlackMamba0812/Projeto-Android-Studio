package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentFormBinding
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.fragment.DatePickerFragment
import com.example.myapplication.fragment.TimerPickerListener
import com.example.myapplication.model.Categoria
import com.example.myapplication.util.MainViewModel
import kotlinx.coroutines.selects.select
import java.time.LocalDate


class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        mainViewModel.listCategoria()

        mainViewModel.dataSelecionada.value = LocalDate.now()

        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
           response -> Log.d("Requisicao", response.body().toString())

            //vamos chamar a função spinnerCategoria, que será o corpo da nossa resposta
            spinnerCategoria(response.body())
        }

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner){
            selectedDate -> binding.editData.setText(selectedDate.toString())
        }

        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        binding.editData.setOnClickListener{
            DatePickerFragment(this)
                .show(parentFragmentManager, "DatePicker")
        }

        return binding.root

    }

    //Criar um novo método para criar a spinner, serve para popular uma spinner de Categoria

    /* Sinalizar com ? para informar que a variável Categoria, talvez pode vir nula, ao ser puxado
    do banco de dados.
     */

    fun spinnerCategoria(listCategotia: List<Categoria>?) {
        if (listCategotia != null){

            //referenciar a spinner categoria e depois adaptar ela para receber uma Array
            binding.spinnerCategoria.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                    listCategotia
                )
        }
    }

    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }
}