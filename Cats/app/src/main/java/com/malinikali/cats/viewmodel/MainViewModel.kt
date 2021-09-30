package com.malinikali.cats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malinikali.cats.models.FactItem
import com.malinikali.cats.repository.FactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val factRepository: FactRepository):ViewModel(){

    private val  _fact = MutableLiveData<FactItem>()
    val fact: LiveData<FactItem> get() = _fact

    private fun getRandomFact() {

        viewModelScope.launch {
            factRepository.getRandomFact().let {
                response ->

                if (response.isSuccessful){
                    _fact.postValue(response.body())
                } else {
                    Log.d("FACTVIEWMODEL","${response.body()}")
                }
            }
        }

    }


    fun generateFact(){
        getRandomFact()
    }





}