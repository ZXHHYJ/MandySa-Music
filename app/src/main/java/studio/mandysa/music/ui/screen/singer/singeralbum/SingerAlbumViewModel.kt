package studio.mandysa.music.ui.screen.singer.singeralbum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class SingerAlbumViewModel(id: String) : ViewModel() {

    val albumSource = Pager(PagingConfig(pageSize = 30)) {
        SingerAlbumPagingSource(id)
    }.flow.cachedIn(viewModelScope)

}