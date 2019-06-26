package uk.co.applylogic.playground.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import uk.co.applylogic.playground.mvvm.model.Blog

class MainViewModel(private val app: Application, private val savedStateHandle: SavedStateHandle) :
    AndroidViewModel(app) {

    lateinit var movieRepository: BlogRepository
    val allBlog: LiveData<List<Blog>> get() = movieRepository.getMutableLiveData()

    companion object {
        private val DATA = "DATA"
    }
    private val _data : MutableLiveData<String> = savedStateHandle.getLiveData(DATA)
    // Only expose a immutable LiveData
    val data : LiveData<String> = _data

    fun saveData(data: String) {
        savedStateHandle.set(DATA, data)
    }

    fun getData(): String {
        return savedStateHandle.get(DATA) ?: ""
    }
}