package com.example.githubscreen.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.githubscreen.R
import com.example.githubscreen.databinding.FragmentProfileScreenBinding
import com.example.githubscreen.network.NetworkConnectionLiveData
import com.example.githubscreen.utils.Status
import com.example.githubscreen.viewmodel.ProfileViewmodel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileScreen : Fragment() {

    @Inject
    lateinit var networkConnectionLiveData : NetworkConnectionLiveData
    private var binding : FragmentProfileScreenBinding?=null
    private val profileViewmodel : ProfileViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileScreenBinding.inflate(layoutInflater)
        profileViewmodel.githubprofiledetails()
        networkConnectionLiveData.observe(viewLifecycleOwner){
            if (it.equals(true))
            {
                profileViewmodel.githubprofiledetails()
            }
        }
        profileViewmodel._githubdata.observe(viewLifecycleOwner){
            Log.e("Data","Data"+it)
            when(it.status)
            {
                Status.SUCCESS->{
                    binding?.topProgress?.visibility = View.INVISIBLE
                    binding!!.tvname.setText(it.data?.name)
                    binding!!.tvemail.setText(it.data?.bio.toString())
                    Picasso.get()
                        .load(it?.data?.avatar_url)
                        .into(binding!!.ivprofile)
                    binding!!.createddate.setText("Created Date : "+it.data?.created_at)
                    Log.e("Data","Data"+it.data?.name)
                }
                Status.ERROR -> {
                    binding?.topProgress?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding?.topProgress?.visibility = View.VISIBLE

                }
            }
        }
        return binding!!.root
    }


}

