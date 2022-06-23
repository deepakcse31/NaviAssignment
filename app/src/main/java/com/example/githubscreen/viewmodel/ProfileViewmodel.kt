package com.example.githubscreen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubscreen.data.repository.MainRepository
import com.example.githubscreen.model.githubresponse
import com.example.githubscreen.utils.NetworkHelper
import com.example.githubscreen.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileViewmodel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel()  {
    private val githubdata =MutableLiveData<Resource<githubresponse>>()
    val _githubdata : LiveData<Resource<githubresponse>> get() = githubdata

    fun githubprofiledetails()
    {
        Log.e("profiledetails","profiledetails"+"profiledetails")
        viewModelScope.launch {
            try {
                githubdata.postValue(Resource.loading(null))
                if (networkHelper.isNetworkConnected())
                {
                    mainRepository.getUsers().let {
                        if (it.isSuccessful)
                        {
                            Log.e("Succesful","Succesful"+"Sucessful")
                            githubdata.value = Resource.success(it.body())
                        }
                        else{
                            Log.e("UnSuccesful","UnSuccesful"+"UnSucessful")
                            githubdata.postValue(Resource.error(
                                    it.errorBody().toString(),
                                    null
                                )
                            )
                        }
                    }
                }
                else{
                    githubdata.postValue(Resource.error("No Internet Connection",null))
                }
            }catch (e: Exception) {
                Log.d("LogTag", e.toString())
            }
        }
    }
}