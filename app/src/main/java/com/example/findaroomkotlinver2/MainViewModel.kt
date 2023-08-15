package com.example.findaroomkotlinver2

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findaroomkotlinver2.data.model.post.PostHome
import com.example.findaroomkotlinver2.data.repository.Repository
import com.example.findaroomkotlinver2.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val app: Application,
    private val repository: Repository
) : ViewModel() {
    val allListPostAdsHome: MutableLiveData<Resource<PostHome>> = MutableLiveData()
    val allListPostHome: MutableLiveData<Resource<PostHome>> = MutableLiveData()

    fun getAllListPostAdsHome() = viewModelScope.launch(Dispatchers.IO) {
        allListPostAdsHome.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = repository.getListPostHomeAds()
                allListPostAdsHome.postValue(apiResult)
            } else {
                allListPostAdsHome.postValue(Resource.Error("Internet is not available"))
            }

        } catch (e: Exception) {
            allListPostAdsHome.postValue(Resource.Error(e.message.toString()))
        }
    }


    fun getAllListPostHome() = viewModelScope.launch(Dispatchers.IO) {
        allListPostHome.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = repository.getListPost()
                allListPostHome.postValue(apiResult)
            } else {
                allListPostHome.postValue(Resource.Error("Internet is not available"))
            }

        } catch (e: Exception) {
            allListPostHome.postValue(Resource.Error(e.message.toString()))
        }
    }


    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}