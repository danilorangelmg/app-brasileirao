package br.com.soccer.feature.home

import androidx.lifecycle.*
import br.com.soccer.domain.soccer.Classification
import br.com.soccer.repositorie.soccer.SoccerRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SoccerRepository) : ViewModel(), LifecycleObserver {

    val errorLiveData: LiveData<String> get() = _errorLiveData
    val itemsLiveData: LiveData<List<Classification>> get() = _itemsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    private val _itemsLiveData = MutableLiveData<List<Classification>>()

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        loadScore()
    }

    fun loadScore() {
        viewModelScope.launch {
            try {
                _itemsLiveData.value = repository.getScore().table
            } catch (e: Exception) {
                _errorLiveData.value = e.message
            }
        }
    }

    fun teste() = listOf<Classification>()
}