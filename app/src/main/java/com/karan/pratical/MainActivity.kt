package com.karan.pratical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.pratical.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var biding: ActivityMainBinding
    lateinit var artistViewModel: ArtistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

//        val artistData : ArrayList<Result>()

        val artistApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val artistRepo = ArtistRepo(artistApi)

        artistViewModel =
            ViewModelProvider(this, ArtistViewModelFactory(artistRepo))[ArtistViewModel::class.java]



        val data = ArrayList<Result>()
        val artistAdapter = ArtistAdapter(this@MainActivity, data)
        biding.rvArtist.layoutManager = GridLayoutManager(this, 1)
        biding.rvArtist.adapter = artistAdapter

        artistViewModel.quotes.observe(this, Observer {
            data.addAll(it.results)
            it.results.sortedBy {it.trackName}
            artistAdapter.setData(it.results)
            Log.d("Data: ", it.toString())
        })

        Log.d("Data in dataclass: ", data.toString())

        biding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filter(newText)
                    val filteredlist = ArrayList<Result>()

                    // running a for loop to compare elements.
                    for (item in data) {
                        // checking if the entered string matched with any item of our recycler view.
                        if (item.artistName.lowercase().contains(newText.lowercase(Locale.getDefault()))) {
                            // if the item is matched we are
                            // adding it to our filtered list.
                            if (!filteredlist.contains(item)) {
                                filteredlist.add(item)
                            }
                        }
                        if (item.trackName.lowercase().contains(newText.lowercase(Locale.getDefault()))) {
                            // if the item is matched we are
                            // adding it to our filtered list.
                            if (!filteredlist.contains(item)) {
                                filteredlist.add(item)
                            }
                        }
                    }
                    if (filteredlist.isEmpty()) {
                        // if no item is added in filtered list we are
                        // displaying a toast message as no data found.
                        artistAdapter.setData(filteredlist)

                        Toast.makeText(applicationContext, "No Data Found..", Toast.LENGTH_SHORT).show()
                    } else {
                        // at last we are passing that filtered
                        // list to our adapter class.
                        filteredlist.sortedBy {it.trackName}
                        artistAdapter.setData(filteredlist)
                    }
                }else{
                    data.sortedBy {it.trackName}
                    artistAdapter.setData(data)

                }
                return false
            }
        })


        // launching a new coroutine
//        GlobalScope.launch {
//            val result = artistApi.getArtistData("jack johnson", 50)
//            if (result != null)
//            // Checking the results
//                Log.d("Data: ", result.body().toString())
////            artistData.addAll(result.body())
//        }


    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.

    }
}