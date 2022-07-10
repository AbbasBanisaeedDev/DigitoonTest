package github.abbasbanisaeed.digitoon_test.repository

import github.abbasbanisaeed.digitoon_test.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:ApiServices) {

suspend fun topMoviesList(query:String)=api.moviesTopList(query)

    suspend fun lastMoviesList()=api.moviesLastList()

}