package github.abbasbanisaeed.digitoon_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.abbasbanisaeed.digitoon_test.model.detail.ResponseMovies
import github.abbasbanisaeed.digitoon_test.repository.DetailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository):ViewModel() {

    //Api
    val detailMovie = MutableLiveData<ResponseMovies>()
    val loading = MutableLiveData<Boolean>()

    fun loadDetailMovie(imdbID:String) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.detailMovie(imdbID)
        if (response.isSuccessful) {
            detailMovie.postValue(response.body())
        }
        loading.postValue(false)
    }

}