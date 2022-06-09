package com.example.viewmodelelivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    /*

    LiveData = fará com que o valor sejá imutável, ou seja, nunca muda.

    MutableLiveData = fará com que o valor sejá mutável, ou seja, sofrerá alterações.

     */

    var cont = MutableLiveData<Int>(0)

    fun addNum(){
        cont.value = cont.value?.plus(1)
    }
}