package br.com.soccer.feature.game

import androidx.lifecycle.*
import br.com.soccer.domain.soccer.Turn
import br.com.soccer.repositorie.soccer.SoccerRepository
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

class GameViewModel(private val repository: SoccerRepository) : ViewModel(), LifecycleObserver {

    val errorLiveData: LiveData<String> get() = _errorLiveData
    val itemsLiveData: LiveData<List<Turn>> get() = _itemsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    private val _itemsLiveData = MutableLiveData<List<Turn>>()

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            try {
                _itemsLiveData.value = repository.getGames().turns
            } catch (e: Exception) {
                _errorLiveData.value = e.message
            }
        }
    }
}