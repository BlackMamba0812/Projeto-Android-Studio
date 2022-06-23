package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentFormBinding
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.fragment.DatePickerFragment
import com.example.myapplication.fragment.TimerPickerListener
import com.example.myapplication.model.Categoria
import com.example.myapplication.model.Tarefa
import com.example.myapplication.util.MainViewModel
import kotlinx.coroutines.selects.select
import java.time.LocalDate


class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoriaSelecionada = 0L
    private var tarefaSeleciona: Tarefa? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()

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
            inserirNoBanco()

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

   private fun spinnerCategoria(listCategotia: List<Categoria>?) {
        if (listCategotia != null){

            //referenciar a spinner categoria e depois adaptar ela para receber uma Array
            binding.spinnerCategoria.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                    listCategotia
                )

            binding.spinnerCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                      val selected = binding.spinnerCategoria.selectedItem as Categoria

                        categoriaSelecionada = selected.id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }


                }

        }
    }

   private fun validarCampos(
       nome: String, descricao: String, responsavel: String
   ): Boolean{
       return !(
               (nome == "" || nome.length < 3 || nome.length > 20) ||
                       (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                       (responsavel == "" || responsavel.length < 3 || responsavel.length > 20)
               )
   }

   private fun inserirNoBanco(){
       val nome = binding.editNome.text.toString()
       val desc = binding.editDescricao.text.toString()
       val resp = binding.editResponsavel.text.toString()
       val data = binding.editData.text.toString()
       val status = binding.switchAtivoCard.isChecked
       val categoria = Categoria (categoriaSelecionada, null,null)

       if (validarCampos(nome, desc, resp)){
           val tarefa = Tarefa(0, nome, desc, resp, data, status, categoria)
           mainViewModel.addTarefa(tarefa)
           Toast.makeText(context, "Tarefa Criada!", Toast.LENGTH_SHORT).show()
           findNavController().navigate(R.id.action_formFragment_to_listFragment)

       }else{
           Toast.makeText(context, "Verifique os Campos!", Toast.LENGTH_SHORT).show()

       }

       private fun carregarDados(){
           tarefaSeleciona = mainViewModel.tarefaSelecionada
           if (tarefaSeleciona != null){
               binding.editNome.setText(tarefaSeleciona?.nome)
               binding.editDescricao.setText(tarefaSeleciona?.descricao)
               binding.editResponsavel.setText(tarefaSeleciona?.responsavel)
               binding.editData.setText(tarefaSeleciona?.data)
               binding.switchAtivoCard.isChecked = tarefaSeleciona?.status!!
           }

       }

   }

    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }
}