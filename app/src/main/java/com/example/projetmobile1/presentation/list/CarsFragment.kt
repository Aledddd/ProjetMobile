package com.example.projetmobile1.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile1.R
import com.example.projetmobile1.presentation.Singleton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class CarsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = CarsAdapter(listOf(),::onClickedCar)
    private val layout = LinearLayoutManager(context)
    private val key: String = "CarsList"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cars_list, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.cars_recyclerview)
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter

        val list: List<CarsResponse> = getListFromCache()
        if(list.isEmpty()) {
            callApi()
        }else{
            showList(list)
        }

    }

    private fun showList(carsResponse: List<CarsResponse>) {
        adapter.updateList(carsResponse)
    }

    private fun getListFromCache(): List<CarsResponse>{
        val carsList: List<CarsResponse>
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val carsResponse = sharedPref?.getString(key, null)
         return if(carsResponse !=null){
            val gson = Gson()
            val type : Type = object : TypeToken<List<CarsResponse?>?>() {}.type
            carsList = gson.fromJson(carsResponse, type)
            carsList
        }else {
             return emptyList()
       }
    }


    private fun saveListIntoCache(carsResponse :List<CarsResponse>) {
        val gson = Gson()
        val jsonFavorites = gson.toJson(carsResponse)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(key, jsonFavorites)
            apply()
        }
    }

    private fun callApi() {
        Singleton.carsApi.getCars().enqueue(object : Callback<List<CarsResponse>> {

            override fun onResponse(
                call: Call<List<CarsResponse>>,
                response: Response<List<CarsResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val carsResponse: List<CarsResponse> = response.body()!!
                    saveListIntoCache(carsResponse)
                    showList(carsResponse)
                }
            }

            override fun onFailure(call: Call<List<CarsResponse>>, t: Throwable) {
            }
        })
    }

    private fun onClickedCar(carsResponse: CarsResponse) {
        findNavController().navigate(R.id.NavigateToCarDetailFragment, bundleOf(
                "CarId" to carsResponse.id,
                "CarModel" to carsResponse.model,
                "CarPrice" to carsResponse.price,
                "CarMake" to carsResponse.make,
                "CarHorsePower" to carsResponse.horsepower,
                "CarImage" to carsResponse.img_url
            ))
    }
}