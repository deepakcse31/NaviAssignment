package com.example.githubscreen.viewmodel

import android.content.res.Resources
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
        viewModelScope.launch {
            try {
                coroutineScope {
                    githubdata.postValue(Resource.loading(null))
                    if (networkHelper.isNetworkConnected())
                    {
                        mainRepository.getUsers().let {
                            if (it.isSuccessful)
                            {
                                githubdata.postValue(Resource.success(it.body()))
                            }
                            else{
                                githubdata.postValue(
                                    Resource.error(
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
                }
            }catch (e: Exception) {

            }
        }
    }
}