package studio.mandysa.music.ui.screen.me.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class LikeViewModel(id: String) : ViewModel() {

    val songs = Pager(PagingConfig(15)) {
        LikePagingSource(id)
    }.flow.cachedIn(viewModelScope)

}