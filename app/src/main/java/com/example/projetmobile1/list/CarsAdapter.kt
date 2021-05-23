package com.example.projetmobile1.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile1.R
import com.example.projetmobile1.presentation.api.CarsResponse



class CarsAdapter(private var dataSet: List<CarsResponse>,var listener: ((CarsResponse)->Unit)? = null) :

    RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.voiture)

        }
    }

    fun updateList(list: List<CarsResponse>){
            dataSet = list
            notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cars_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val cars : CarsResponse = dataSet[position]
        viewHolder.textView.text = cars.make +"   "+cars.model
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(cars)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


