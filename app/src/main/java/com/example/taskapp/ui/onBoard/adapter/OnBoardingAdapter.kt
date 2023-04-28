package com.example.taskapp.ui.onBoard.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.R
import com.example.taskapp.databinding.ItemOnboardBinding
import com.example.taskapp.model.OnBoard
import com.example.taskapp.utils.loadImage


class OnBoardingAdapter(private val onClick: (OnBoard) -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf(
        OnBoard(
            title = "Title 1",
            desc = "Description 1",
            image = "https://cdn0.iconfinder.com/data/icons/urban-tribes-people-rounded/110/Geek-512.png"

        ),
        OnBoard(
            title = "Title 2",
            desc = "Description 2",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-hvfPNnkZrEU2dLqNcez61LI5svOZhAJVGw&usqp=CAU"

        ),
        OnBoard(
            title = "Title 3",
            desc = "Description 3",
            image = "https://img.freepik.com/premium-vector/geek-logo-icon-vector-design-illustration_487414-1224.jpg?w=2000"

        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.btnStart.setOnClickListener {
                onClick(onBoard)
            }
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.ivBoard.loadImage(onBoard.image)
        }
    }
}
