package github.abbasbanisaeed.digitoon_test.repository

import github.abbasbanisaeed.digitoon_test.api.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {

    suspend fun detailMovie(imdbID:String)=api.detailMoviesList(imdbID)
}