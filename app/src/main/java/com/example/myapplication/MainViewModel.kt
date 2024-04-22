package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.StringDao
import com.example.myapplication.db.StringEntity
import kotlinx.coroutines.launch

class MainViewModel(private val dao: StringDao): ViewModel() {
    private val _showList = MutableLiveData<List<StringEntity>>()
    val showList: LiveData<List<StringEntity>> = _showList


    fun getList() {
        viewModelScope.launch {
            val results = dao.showAll()
            _showList.value = results
        }
    }
    
    fun saveItem(str: String) {
        viewModelScope.launch {
            dao.addString(
                StringEntity(
                    0, str
                )
            )
        }
    }

    fun deleteItem(item: StringEntity) {
        viewModelScope.launch {
            dao.deleteString(item)
        }
    }
}