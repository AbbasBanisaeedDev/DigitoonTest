package github.abbasbanisaeed.digitoon_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.abbasbanisaeed.digitoon_test.model.ResponseMoviesTop
import github.abbasbanisaeed.digitoon_test.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMoviesTop>()
    val lastMoviesList = MutableLiveData<ResponseMoviesTop>()
    val loading = MutableLiveData<Boolean>()
    fun loadTopMoviesList(query:String)=viewModelScope.launch {
        val response=repository.topMoviesList(query)
        if (response.isSuccessful){
            topMoviesList.postValue(response.body())
        }
    }

    fun lastTopMoviesList()=viewModelScope.launch {
        loading.postValue(true)
        val response=repository.lastMoviesList()
        if (response.isSuccessful){
            lastMoviesList.postValue(response.body())
        }
        loading.postValue(false)
    }

}