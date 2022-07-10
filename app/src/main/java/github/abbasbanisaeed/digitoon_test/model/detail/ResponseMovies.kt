package github.abbasbanisaeed.digitoon_test.model.detail


import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    @SerializedName("Actors")
    val actors: String?, // Christian Bale, Michael Caine, Ken Watanabe
    @SerializedName("Awards")
    val awards: String?, // Nominated for 1 Oscar. 13 wins & 79 nominations total
    @SerializedName("BoxOffice")
    val boxOffice: String?, // $206,863,479
    @SerializedName("Country")
    val country: String?, // United States, United Kingdom
    @SerializedName("DVD")
    val dVD: String?, // 18 Oct 2005
    @SerializedName("Director")
    val director: String?, // Christopher Nolan
    @SerializedName("Genre")
    val genre: String?, // Action, Crime, Drama
    @SerializedName("imdbID")
    val imdbID: String?, // tt0372784
    @SerializedName("imdbRating")
    val imdbRating: String?, // 8.2
    @SerializedName("imdbVotes")
    val imdbVotes: String?, // 1,432,902
    @SerializedName("Language")
    val language: String?, // English, Mandarin
    @SerializedName("Metascore")
    val metascore: String?, // 70
    @SerializedName("Plot")
    val plot: String?, // After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.
    @SerializedName("Poster")
    val poster: String?, // https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg
    @SerializedName("Production")
    val production: String?, // N/A
    @SerializedName("Rated")
    val rated: String?, // PG-13
    @SerializedName("Ratings")
    val ratings: List<Rating?>?,
    @SerializedName("Released")
    val released: String?, // 15 Jun 2005
    @SerializedName("Response")
    val response: String?, // True
    @SerializedName("Runtime")
    val runtime: String?, // 140 min
    @SerializedName("Title")
    val title: String?, // Batman Begins
    @SerializedName("Type")
    val type: String?, // movie
    @SerializedName("Website")
    val website: String?, // N/A
    @SerializedName("Writer")
    val writer: String?, // Bob Kane, David S. Goyer, Christopher Nolan
    @SerializedName("Year")
    val year: String? // 2005
) {
    data class Rating(
        @SerializedName("Source")
        val source: String?, // Internet Movie Database
        @SerializedName("Value")
        val value: String? // 8.2/10
    )
}