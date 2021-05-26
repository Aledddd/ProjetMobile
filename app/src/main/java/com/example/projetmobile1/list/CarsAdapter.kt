package com.example.projetmobile1.list

import android.annotation.SuppressLint

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetmobile1.R
import com.example.projetmobile1.presentation.api.CarsResponse
import de.hdodenhof.circleimageview.CircleImageView


class CarsAdapter(private var dataSet: List<CarsResponse>, private var listener: ((CarsResponse)->Unit)? = null) :

    RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val circleImageView: CircleImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.voiture)
            circleImageView = view.findViewById(R.id.car_img)
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
    @SuppressLint("SetTextI18n", "CheckResult", "ResourceType")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val cars : CarsResponse = dataSet[position]
        viewHolder.textView.text = cars.make +"   "+cars.model
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(cars)
        }
            Glide
                .with(viewHolder.itemView.context)
                .load(cars.img_url)
                .centerCrop()
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(viewHolder.circleImageView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


