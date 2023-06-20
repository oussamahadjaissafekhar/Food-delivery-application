package com.example.RestaurentApp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.RestaurentApp.entity.OrderItem
import com.example.movieexample.databinding.Fragment1LayoutBinding

class CardAdapter(val ctx:Context, val data: List<OrderItem>):RecyclerView.Adapter<CardAdapter.CardHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        return CardHolder(Fragment1LayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(data[position].image)
                .into(itemImage)
            menuName.text = data[position].name
            menuPrice.text = data[position].price.toString()
            quantity.text = data[position].quantity.toString()
            //textView2.text = R.drawable.cheeseburger.toString(

        }
        holder.binding.btnQuantitySub.setOnClickListener()
        {
            var quantity = Integer.parseInt(holder.binding.quantity.text.toString())
            quantity--
            if (quantity >= 0) {
                holder.binding.quantity.text = quantity.toString()

            }
        }
        holder.binding.btnQuantityAdd.setOnClickListener()
      {
          var quantity = Integer.parseInt(holder.binding.quantity.text.toString())
          quantity ++
          if(quantity >= 0){
              holder.binding.quantity.text = quantity.toString()

          }

        }

    }
    class CardHolder(val binding: Fragment1LayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



