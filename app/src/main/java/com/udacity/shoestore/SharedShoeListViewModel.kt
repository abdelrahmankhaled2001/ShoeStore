package com.udacity.shoestore

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedShoeListViewModel : ViewModel(){
    private val _shoeList =MutableLiveData<MutableList<Shoe>>()
    val shoeList:LiveData<MutableList<Shoe>>
        get()=_shoeList

    init{
    _shoeList.value=mutableListOf()


    }

    fun addToShoeList(shoe:Shoe?){
        shoe?.let {
            _shoeList.value?.add(shoe)

        }
    }





}
