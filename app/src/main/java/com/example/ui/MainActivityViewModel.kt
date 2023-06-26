package com.example.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivityViewModel(
    private val application: Application,
    private val imageRepository: ImageRepository
)  : ViewModel() {

    class Factory(
        private val application: Application,
        private val imageRepository: ImageRepository,
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            MainActivityViewModel(application, imageRepository) as T
    }

    private val _list = MutableLiveData<List<ImageItem>>()
    val list: LiveData<List<ImageItem>> = _list

    fun setImageList() {
        _list.value = this.imageRepository.getImageList()
    }
}



