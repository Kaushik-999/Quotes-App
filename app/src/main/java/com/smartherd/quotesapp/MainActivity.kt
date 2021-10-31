package com.smartherd.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartherd.quotesapp.services.QuotesService
import com.smartherd.quotesapp.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
 Retiofit Steps:

 1. setup the "interface" service
 2. create the "service builder"
        it helps to call the function of the interface
 3. initialize the service builder in the "activity"
        and call the functions of the interface
*/





class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
        mAdapter = QuoteAdapter()

        quoteRecyclerView.adapter = mAdapter
    }

    // call the api
    fun fetchData() {
        progressBar.visibility = View.VISIBLE

        // instanciate the the Service
        val quoteService = ServiceBuilder.buildService(QuotesService::class.java)
        val requestCall = quoteService.getQuotes()
        //Async "Network Call"
        requestCall.enqueue(object: Callback<ArrayList<Quotes>>{
            override fun onResponse(call: Call<ArrayList<Quotes>>?, response: Response<ArrayList<Quotes>>) {
                if(response.isSuccessful ){
                    progressBar.visibility = View.GONE
                    val quotesList = response.body()!!
                    mAdapter.updateQuotes(quotesList)
                } else { // Aplication Level Failure
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity,"Failed to retrive data", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ArrayList<Quotes>>?, t: Throwable?) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity,"Error Occurred", Toast.LENGTH_LONG).show()
            }
        })
    }

}