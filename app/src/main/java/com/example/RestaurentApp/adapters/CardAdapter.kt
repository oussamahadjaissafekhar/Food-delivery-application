package com.example.RestaurentApp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.RestaurentApp.AppDatabase
import com.example.RestaurentApp.entity.OrderItem
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.databinding.Fragment1LayoutBinding

class CardAdapter(val ctx:Context, val data:MutableList<OrderItem>,val vm:MyModel):RecyclerView.Adapter<CardAdapter.CardHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        return CardHolder(Fragment1LayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size
    fun removeItem(position: Int) {
        if (position >= 0 && position < data.size) {
        data.removeAt(position)
          notifyDataSetChanged()
        }}
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(data[position].image)
                .into(itemImage)

            menuName.text = data[position].name.toString()
            menuPrice.text = data[position].price.toString()
            quantity.text = data[position].quantity.toString()
            //textView2.text = R.drawable.cheeseburger.toString(

        holder.binding.btnQuantitySub.setOnClickListener()
        {
            var quantity = Integer.parseInt(holder.binding.quantity.text.toString())
            if (quantity > 1) {
                quantity--

                holder.binding.quantity.text = quantity.toString()
                data[position].quantity=Integer.parseInt(quantity.toString())
              //update room databse
                AppDatabase.buildDatabase(ctx)?.getOrderItemDo()?.updateOrderItems(data)
                val updatedItems= AppDatabase.buildDatabase(ctx)?.getOrderItemDo()?.getOrderItems()!!
                    .toMutableList()

                vm.updateOrderItems(updatedItems)


            }
            else{
                Toast.makeText(ctx, "do you want do delete this item", Toast.LENGTH_SHORT).show()
            }
        }
        holder.binding.btnQuantityAdd.setOnClickListener()
      {
          var quantity = Integer.parseInt(holder.binding.quantity.text.toString())
          quantity ++
          if(quantity > 0){
              holder.binding.quantity.text = quantity.toString()
              data[position].quantity=Integer.parseInt(quantity.toString())
              //update room databse
              AppDatabase.buildDatabase(ctx)?.getOrderItemDo()?.updateOrderItems(data)
              val updatedItems= AppDatabase.buildDatabase(ctx)?.getOrderItemDo()?.getOrderItems()!!
                  .toMutableList()

              vm.updateOrderItems(updatedItems)
          }

        }
       holder.binding.deleteitem.setOnClickListener{
           AppDatabase.buildDatabase(ctx)?.getOrderItemDo()?.removeMenuItemFromOrder(data[position])
           removeItem(position)
           val updatedItems= AppDatabase.buildDatabase(ctx)?.getOrderItemDo()?.getOrderItems()!!
               .toMutableList()

           vm.updateOrderItems(updatedItems)


       }


    }}
    class CardHolder(val binding: Fragment1LayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



