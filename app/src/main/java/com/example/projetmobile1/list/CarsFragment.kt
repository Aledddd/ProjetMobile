package com.example.projetmobile1.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile1.R
import com.example.projetmobile1.presentation.api.CarsApi
import com.example.projetmobile1.presentation.api.CarsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = CarsAdapter(listOf(),::onClickedCar)


    private val layout = LinearLayoutManager(context)

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


        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-a858ac5986-carsapi1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val carsApi: CarsApi = retrofit.create(CarsApi::class.java)

        carsApi.getCars().enqueue(object : Callback<List<CarsResponse>> {

            override fun onResponse(call: Call<List<CarsResponse>>, response: Response<List<CarsResponse>>) {
                if(response.isSuccessful &&response.body()!=null){
                    val carsResponse: List<CarsResponse> = response.body()!!
                    adapter.updateList(carsResponse)
                }
            }

            override fun onFailure(call: Call<List<CarsResponse>>, t: Throwable) {
            }
        })
    }
    private fun onClickedCar(carsResponse: CarsResponse) {
        findNavController().navigate(R.id.NavigateToCarDetailFragment)
    }
}