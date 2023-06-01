package com.karan.pratical

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArtistAdapter(private val context: Context,private var artistList: List<Result>)  :
    RecyclerView.Adapter<ArtistAdapter.viewHolder>() {


    fun setData(artistListData: List<Result>){
        artistList = artistListData
        artistList.sortedBy {it.trackName}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_sample, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val ArtistData = artistList[position]

        Glide.with(context).load(ArtistData.artworkUrl100).into(holder.igUserImage);

        holder.txArtistName.text = ArtistData.artistName
        holder.txTrackName.text = ArtistData.trackName

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("artworkUrl100", ArtistData.artworkUrl100)
            intent.putExtra("artistName", ArtistData.artistName)
            intent.putExtra("trackName", ArtistData.trackName)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    class viewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val igUserImage: ImageView = itemView.findViewById(R.id.igUserImage)
        val txArtistName: TextView = itemView.findViewById(R.id.txArtistName)
        val txTrackName: TextView = itemView.findViewById(R.id.txTrackName)
    }


}