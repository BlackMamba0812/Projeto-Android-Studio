package com.example.sqlitecomroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitecomroom.data.Usuario
import com.example.sqlitecomroom.databinding.CardLayoutBinding
import com.example.sqlitecomroom.databinding.FragmentListBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var listUser = emptyList<Usuario>()

    class UserViewHolder(binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}