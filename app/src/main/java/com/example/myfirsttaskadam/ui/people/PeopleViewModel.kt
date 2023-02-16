package com.example.myfirsttaskadam.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirsttaskadam.data.model.people.PeopleItemModel
import com.example.myfirsttaskadam.data.model.people.PeopleModel
import com.example.myfirsttaskadam.data.repository.Repository
import com.example.myfirsttaskadam.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
  private  val repository: Repository
) : ViewModel() {

    private var _peopleList = MutableLiveData<NetworkResult<PeopleModel>>()
    val peopleList: LiveData<NetworkResult<PeopleModel>> = _peopleList

    private var _selectedPeople = MutableLiveData<PeopleItemModel>()
    val selectedPeople: LiveData<PeopleItemModel> = _selectedPeople

//    init {
//        getPeopleList()
//    }
//
    fun getPeopleList() {
        viewModelScope.launch {
            _peopleList.value = NetworkResult.Loading()
            val result = repository.getPeople()

            if (result.isSuccessful) {
                //  _peopleList.postValue(result.body())//on background thread:if we've the res, update,
                //  ifnot, wait for it, update when get tge res
                _peopleList.value = NetworkResult.Success(result.body()!!) //executed on mainthread
            } else {
                _peopleList.value = NetworkResult.Error(result.message())
            }
        }
    }
}

