package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
A bridge that tells the recycleView how to display the data we pass in
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickListener: onLongClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    interface onLongClickListener {
        fun onItemLongClicked(position: Int)
    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val context = parent.context
       val inflater = LayoutInflater.from(context)
       //inflate the custom layout
       val itemView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
       //return ViewHolder instance
        return ViewHolder(itemView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the data model based on the position
        val item = listOfItems.get(position)
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
          //Store reference of our layout view
          val textView : TextView

          init{
              textView = itemView.findViewById(android.R.id.text1)

              itemView.setOnLongClickListener {
                  longClickListener.onItemLongClicked(adapterPosition)
                  true
              }
          }
    }

}