package studio.mandysa.music.ui.screen.playlist

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.pop
import studio.mandysa.music.R
import studio.mandysa.music.service.playmanager.PlayManager
import studio.mandysa.music.ui.common.*
import studio.mandysa.music.ui.item.ContentColumnItem
import studio.mandysa.music.ui.item.SongItem
import studio.mandysa.music.ui.screen.DialogDestination
import studio.mandysa.music.ui.screen.ScreenDestination
import studio.mandysa.music.ui.theme.horizontalMargin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    mainNavController: NavController<ScreenDestination>,
    dialogNavController: NavController<DialogDestination>,
    paddingValues: PaddingValues,
    id: String,
    playlistViewModel: PlaylistViewModel = viewModel(factory = viewModelFactory {
        addInitializer(PlaylistViewModel::class) { return@addInitializer PlaylistViewModel(id) }
    })
) {
    val playlistInfo by playlistViewModel.infoModelLiveData.observeAsState()
    val songs by playlistViewModel.songsLiveData.observeAsState()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarState()
    )
    AppScaffold(
        modifier = Modifier
            .statusBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppMediumTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { mainNavController.pop() }) {
                        Icon(Icons.Rounded.ArrowBack, null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }) {
        SwipeRefreshLayout(viewModel = playlistViewModel, modifier = Modifier.padding(it)) {
            AppLazyVerticalGrid(modifier = Modifier.fillMaxSize()) {
                item {
                    ContentColumnItem(
                        dialogNavController = dialogNavController,
                        coverUrl = playlistInfo?.coverImgUrl,
                        title = playlistInfo?.name,
                        message = playlistInfo?.description
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = horizontalMargin)
                                .padding(bottom = 5.dp)
                        ) {
                            MenuItem(
                                modifier = Modifier.weight(1.0f),
                                title = stringResource(id = R.string.play_all),
                                imageVector = Icons.Rounded.PlayArrow,
                                enabled = songs?.isNotEmpty() ?: false
                            ) {
                                PlayManager.play(songs!!, 0)
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            MenuItem(
                                modifier = Modifier.weight(1.0f),
                                title = stringResource(id = R.string.shuffle_play),
                                imageVector = Icons.Rounded.Shuffle,
                                enabled = songs?.isNotEmpty() ?: false
                            ) {
                                PlayManager.shufflePlay(songs!!, 0)
                            }
                        }
                    }
                }
                songs?.let {
                    autoItems(it.size) { pos ->
                        SongItem(dialogNavController, it[pos]) {
                            PlayManager.play(it, pos)
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}