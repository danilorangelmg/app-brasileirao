package br.com.soccer.feature.game.bottomSheet

import androidx.lifecycle.*
import br.com.soccer.domain.soccer.GameDescription
import br.com.soccer.domain.soccer.Turn
import br.com.soccer.repositorie.soccer.SoccerRepository
import kotlinx.coroutines.launch

class GameBottomSheetViewModel(private val repository: SoccerRepository) : ViewModel(), LifecycleObserver {

    val errorLiveData: LiveData<String> get() = _errorLiveData
    val itemsLiveData: LiveData<List<GameDescription>> get() = _itemsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    private val _itemsLiveData = MutableLiveData<List<GameDescription>>()

    fun load(gameId: Long) {
        viewModelScope.launch {
            try {
                _itemsLiveData.value = repository.getGameDescription(gameId)
            } catch (e: Exception) {
                _errorLiveData.value = e.message
            }
        }
    }

}