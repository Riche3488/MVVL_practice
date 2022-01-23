package com.udacity.shoestore.screen.shoeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel :ViewModel(){
    var shoes_list = MutableLiveData<MutableList<Shoe>>()

}