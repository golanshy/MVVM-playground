package uk.co.applylogic.playground.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import uk.co.applylogic.playground.mvvm.model.Blog
import uk.co.applylogic.playground.mvvm.networking.APIInterface

class BlogRepository(private val restApi: APIInterface?) {

    private var movies = mutableListOf<Blog>()
    private var mutableLiveData = MutableLiveData<List<Blog>>()

    fun getMutableLiveData(): MutableLiveData<List<Blog>> {
        CoroutineScope(Dispatchers.IO).launch {
            val request = restApi?.getPopularBlogAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request?.await()
                    movies = response?.blog as MutableList<Blog>
                    mutableLiveData.value = movies
                } catch (e: HttpException) {
                    e.printStackTrace()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }
        return mutableLiveData
    }
}