package com.rohit.roomapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rohit.roomapplication.databinding.ActivityRecyclerAdapterBinding

class RecyclerAdapter(
    var context: RecyclerActivity,
    var list: ArrayList<Title>,
    var recyclerInterferce: RecyclerInterferce
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var tittle_text = view.findViewById<TextView>(R.id.Title_name)
        var descriptor_text = view.findViewById<TextView>(R.id.Description_name)
        var update_text = view.findViewById<Button>(R.id.Update)
        var delete_text = view.findViewById<Button>(R.id.Delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.base_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
  //  override fun getItemId(position: Int): Long {
    //    return super.getItemId(position)}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tittle_text.setText(list[position].title)
        holder.descriptor_text.setText(list[position].description)

        holder.delete_text.setOnClickListener {
            recyclerInterferce.delete(position)
        }
        holder.update_text.setOnClickListener {
            recyclerInterferce.update(position)
        }


    }

}