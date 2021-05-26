package com.example.projetmobile1.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.projetmobile1.R


class CarDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_car_details, container, false)

    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detail1: TextView = view.findViewById(R.id.car_detail1)
        val detail2: TextView = view.findViewById(R.id.car_detail2)
        val detail3: TextView = view.findViewById(R.id.car_detail3)
        val detail4: TextView = view.findViewById(R.id.car_detail4)
        val detail5: TextView = view.findViewById(R.id.car_detail5)

        val id = arguments?.getInt("CarId")
        val make = arguments?.getString("CarMake")
        val model = arguments?.getString("CarModel")
        val horsepower = arguments?.getInt("CarHorsePower")
        val price = arguments?.getInt("CarPrice")
        detail1.text = "ID : "+id.toString()
        detail2.text = "Make : ${make?.uppercase()}"
        detail3.text = "Model : ${model?.uppercase()}"
        detail4.text = "HorsePower : "+horsepower.toString()
        detail5.text = "Price : "+price.toString()+" $"

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.NavigateToCarListFragment)
        }
    }

}