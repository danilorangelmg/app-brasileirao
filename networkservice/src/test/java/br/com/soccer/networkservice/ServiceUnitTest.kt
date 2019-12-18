package br.com.soccer.networkservice

import br.com.soccer.networkservice.model.MovieDetailResponse
import br.com.soccer.networkservice.soccer.SoccerServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ServiceUnitTest {

    @Test
    fun testGetListAllMovies() {
        val response = runBlocking {
            getListAllMoviesFetch("popularity", "2018", "1")
        }

        assertNotNull(response)
        assertTrue(response.results.isNotEmpty())
    }

    private suspend fun getListAllMoviesFetch(popularity: String, year: String, page: String): MoviesResponse {
        return withContext(Dispatchers.IO) {
            SoccerServiceImpl().getMovies(popularity, year, page)
        }
    }

    @Test
    fun testGetMoviesDetail() {
        val response = runBlocking {
            getAllMovieDetailFetch("429617")
        }

        assertNotNull(response)
        assertTrue(response.original_title == "Spider-Man: Far from Home")
    }

    @Test
    fun testGetMoviesDetailError() {
        try {
            runBlocking {
                getAllMovieDetailFetch("bla")
            }
        } catch (e: Exception) {
            assertEquals(e.message, "Erro ao buscar filmes, Tente novamente em alguns instantes!")
        }
    }

    private suspend fun getAllMovieDetailFetch(movieId: String): MovieDetailResponse {
        return withContext(Dispatchers.IO) {
            SoccerServiceImpl().getMoviesDetail(movieId)
        }
    }

}