package com.tencalculus.bibliogram.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tencalculus.bibliogram.network.BooksApiService
import com.tencalculus.bibliogram.models.BooksData
import com.tencalculus.bibliogram.models.LoadStatus
import com.tencalculus.bibliogram.models.asBooksData
import kotlinx.coroutines.launch

class HomeFragmentViewModel(searchInput: String) : ViewModel() {


    private val _receivedResponse = MutableLiveData<List<BooksData>>()
    val receivedResponse: LiveData<List<BooksData>>
    get() = _receivedResponse

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
    get() = _status


    val searchTerm = searchInput


    init {
        getBooks(searchTerm)
    }


    fun getBooks(query: String){
        viewModelScope.launch {
            _status.value = LoadStatus.LOADING
            try {
                val response = BooksApiService.retrofitService.getBooksData(query)
                _receivedResponse.value = asBooksData(response)
                _status.value = LoadStatus.SUCCESS
            }catch (e: Exception){
                Log.e("getbooks", "$e")
                _status.value = LoadStatus.FAILED
            }
        }
    }
}