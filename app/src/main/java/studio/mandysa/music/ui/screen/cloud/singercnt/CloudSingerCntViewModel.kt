package studio.mandysa.music.ui.screen.cloud.singercnt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import studio.mandysa.music.logic.config.api

class CloudSingerCntViewModel(id: String) : ViewModel() {
    val singerInfo = flow {
        emit(api.getSingerDetails(id = id))
    }.asLiveData(viewModelScope.coroutineContext)

    val songs = flow {
        emit(api.getSingerHotSong(id))
    }.asLiveData(viewModelScope.coroutineContext)
}