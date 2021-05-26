package com.example.projetmobile1.list

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetmobile1.R
import com.example.projetmobile1.presentation.api.CarsResponse
import de.hdodenhof.circleimageview.CircleImageView
import java.net.URL


class CarsAdapter(private var dataSet: List<CarsResponse>, private var listener: ((CarsResponse)->Unit)? = null) :

    RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView_list: TextView
        val circleImageView_list: CircleImageView
       // val textView_details: TextView
       // val circleImageView_details: CircleImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView_list = view.findViewById(R.id.voiture)
            //textView_details = view.findViewById(R.id.car_details)
            circleImageView_list = view.findViewById(R.id.car_img)
            //circleImageView_details = itemView.findViewById(R.id.car_details_img)
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
    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val cars : CarsResponse = dataSet[position]
        viewHolder.textView_list.text = cars.make +"   "+cars.model
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(cars)
        }
            Glide
                .with(viewHolder.itemView.context)
                .load(cars.img_url)
                .centerCrop()
                .into(viewHolder.circleImageView_list)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


