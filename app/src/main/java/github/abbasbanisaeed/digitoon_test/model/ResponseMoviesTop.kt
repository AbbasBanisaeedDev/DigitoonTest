package github.abbasbanisaeed.digitoon_test.model


import com.google.gson.annotations.SerializedName

data class ResponseMoviesTop(
    @SerializedName("Response")
    val response: String?, // True
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String? // 1232
) {
    data class Search(
        @SerializedName("imdbID")
        val imdbID: String, // tt0107362
        @SerializedName("Poster")
        val poster: String?, // https://m.media-amazon.com/images/M/MV5BNjdhOGY1OTktYWJkZC00OGY5LWJhY2QtZmQzZDA2MzY5MmNmXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SX300.jpg
        @SerializedName("Title")
        val title: String?, // Last Action Hero
        @SerializedName("Type")
        val type: String?, // movie
        @SerializedName("Year")
        val year: String? // 1993
    )
}