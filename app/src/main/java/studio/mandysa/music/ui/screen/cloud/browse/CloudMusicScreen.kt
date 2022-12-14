package studio.mandysa.music.ui.screen.cloud.browse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Equalizer
import androidx.compose.material.icons.rounded.QueueMusic
import androidx.compose.material.icons.rounded.Radio
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import studio.mandysa.music.R
import studio.mandysa.music.ui.common.*
import studio.mandysa.music.ui.item.SubTitleItem
import studio.mandysa.music.ui.item.PlaylistItem
import studio.mandysa.music.ui.item.RoundIconItem
import studio.mandysa.music.ui.screen.DialogDestination
import studio.mandysa.music.ui.screen.ScreenDestination
import studio.mandysa.music.ui.theme.*

@Composable
private fun BannerItem(typeTitle: String, bannerUrl: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = roundedCornerShape,
            modifier = Modifier
                .height(140.dp)
                .clickable {
                    PopWindows.postValue("?????????????????????")
                }
        ) {
            AsyncImage(
                model = bannerUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = typeTitle,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = textColor,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CloudMusicScreen(
    mainNavController: NavController<ScreenDestination>,
    dialogNavController: NavController<DialogDestination>,
    paddingValues: PaddingValues,
    cloudViewModel: CloudViewModel = viewModel()
) {
    val userInfo by cloudViewModel.userInfoLiveData.observeAsState()
    val bannerItems by cloudViewModel.bannersLiveData.observeAsState()
    val recommendSongs by cloudViewModel.recommendSongBeanLiveData.observeAsState()
    val recommendPlaylist by cloudViewModel.recommendPlaylistLiveData.observeAsState()
    val playlistSquare by cloudViewModel.playlistSquareLiveData.observeAsState()
    //val toplist by browseViewModel.toplistLiveData.observeAsState()
    Preview(refresh = { cloudViewModel.network() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            item {
                SearchBar(click = { mainNavController.navigate(ScreenDestination.Search) }) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.search_hint),
                        modifier = Modifier.weight(1.0f)
                    )
                    userInfo?.let {
                        AppRoundAsyncImage(
                            modifier = Modifier.size(30.dp),
                            url = it.avatarUrl
                        ) {
                            mainNavController.navigate(ScreenDestination.CloudMeScreen)
                        }
                    }
                }
            }
            item {
                if (isMatePad) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = horizontalMargin),
                        horizontalArrangement = Arrangement.spacedBy(horizontalMargin / 2)
                    ) {
                        bannerItems?.let { it ->
                            items(it) {
                                BannerItem(typeTitle = it.typeTitle, bannerUrl = it.pic)
                            }
                        }
                    }
                } else {
                    Column {
                        val pagerState = rememberPagerState()
                        bannerItems?.let {
                            HorizontalPager(
                                count = it.size,
                                itemSpacing = horizontalMargin,
                                contentPadding = PaddingValues(horizontal = horizontalMargin),
                                state = pagerState
                            ) { index ->
                                val model = it[index]
                                BannerItem(typeTitle = model.typeTitle, bannerUrl = model.pic)
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                activeColor = onBackground,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = horizontalMargin),
                            )
                        }
                    }
                }
            }
            item {
                LazyRow(
                    modifier = Modifier.padding(top = 10.dp),
                    contentPadding = PaddingValues(horizontal = horizontalMargin),
                    horizontalArrangement = Arrangement.spacedBy(horizontalMargin / 2)
                ) {
                    item {
                        RoundIconItem(
                            icon = Icons.Rounded.Radio,
                            title = stringResource(id = R.string.fm)
                        ) {

                        }
                    }
                    item {
                        RoundIconItem(
                            icon = Icons.Rounded.Equalizer,
                            title = stringResource(id = R.string.toplist)
                        ) {
                            mainNavController.navigate(ScreenDestination.CloudToplistScreen)
                        }
                    }
                    item {
                        RoundIconItem(
                            icon = Icons.Rounded.QueueMusic,
                            title = stringResource(id = R.string.playlist)
                        ) {

                        }
                    }
                }
            }
            item {
                SubTitleItem(stringResource(R.string.recommend_playlist))
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = horizontalMargin),
                    horizontalArrangement = Arrangement.spacedBy(horizontalMargin / 2),
                ) {
                    recommendPlaylist?.let {
                        items(it) { model ->
                            PlaylistItem(title = model.name, coverUrl = model.picUrl) {
                                mainNavController.navigate(ScreenDestination.CloudPlaylistCnt(model.id))
                            }
                        }
                    }
                }
            }
            item {
                SubTitleItem(stringResource(R.string.playlist_square))
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = horizontalMargin),
                    horizontalArrangement = Arrangement.spacedBy(horizontalMargin / 2),
                ) {
                    playlistSquare?.let {
                        items(it) { model ->
                            PlaylistItem(title = model.name, coverUrl = model.picUrl) {
                                mainNavController.navigate(ScreenDestination.CloudPlaylistCnt(model.id))
                            }
                        }
                    }
                }
            }
            /*item {
                ItemSubTitle(stringResource(R.string.toplist))
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = horizontalMargin),
                    horizontalArrangement = Arrangement.spacedBy(horizontalMargin / 2),
                ) {
                    toplist?.let {
                        items(it) { model ->
                            PlaylistItem(title = model.name, coverUrl = model.coverImgUrl) {
                                mainNavController.navigate(ScreenDestination.Playlist(model.id))
                            }
                        }
                    }
                }
            }*/
            item {
                SubTitleItem(stringResource(R.string.recommend_song))
            }
            recommendSongs?.let {
                items(it.size) { index ->
                    /*SongItem(dialogNavController, it[index]) {
                        PlayManager.play(it, index)
                    }*/
                }
            }
            item {
                Spacer(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}