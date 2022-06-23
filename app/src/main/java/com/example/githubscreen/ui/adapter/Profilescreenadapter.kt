package com.example.githubscreen.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubscreen.databinding.ProfilecardBinding
import com.example.githubscreen.model.githubresponse
import com.example.githubscreen.utils.Resource
import com.squareup.picasso.Picasso

class Profilescreenadapter(private val context: Context,val list : List<Resource<githubresponse>>) : RecyclerView.Adapter<Profilescreenadapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }
    inner class ViewHolder(val binding: ProfilecardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val result = list[position]

        holder.binding.apply {
            title.text="Title :- "+result.data?.get(position)?.title
            name.text="Name :- "+result.data?.get(position)?.user?.login
            if (result.data?.get(position)?.created_at?.toString().equals(null))
            {
                createddate.text= "Created Date Not Found"
                createddate.setTextColor(Color.parseColor("#A30000"));
            }
            else{

                createddate.text= result.data?.get(position)?.closed_at.toString()
            }
            if (result.data?.get(position)?.closed_at?.toString().equals(null))
            {

                tvcloseddate.text= "Closed Date Not Found"
                tvcloseddate.setTextColor(Color.parseColor("#A30000"));
            }else{

                Log.e("Not Working-->","Not Working-->"+"Not Working-->")
                tvcloseddate.text=result.data?.get(position)?.closed_at.toString()
            }

            Picasso.get()
                .load(result.data?.get(position)?.user?.avatar_url)
                .into(ivprofile)
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=ProfilecardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(v)
    }
}