package com.example.RestaurentApp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.RestaurentApp.entity.Menu
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.R
import com.example.movieexample.databinding.MenuLayoutBinding

class MenuAdapter(val ctx:Context, val data:List<Menu>, val vm: MyModel):RecyclerView.Adapter<MenuAdapter.MyViewHolder>()  {
 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MenuLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
     override fun getItemCount() = data.size

    class MyViewHolder(val binding: MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       /* vm.positionMenu=position
       val pos=vm.position*/

        val currentItem = data[position]


        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(currentItem!!.image)
                .into(imageView)

            textView2.text = data[position].name
            //textView2.text = R.drawable.cheeseburger.toString()
            textView3.text = data[position].price.toString()+"DA"
        }
        holder.itemView.setOnClickListener(){
            view: View ->view.findNavController().navigate(R.id.action_restaurentFragment_to_menuFragment)
            vm.menuId = data[position].idMenu
            vm.positionMenu = position
        }
    }
}



