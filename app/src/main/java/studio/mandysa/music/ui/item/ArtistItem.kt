package studio.mandysa.music.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import studio.mandysa.music.R
import studio.mandysa.music.service.playmanager.bean.SongBean
import studio.mandysa.music.ui.common.AppRoundAsyncImage
import studio.mandysa.music.ui.theme.horizontalMargin
import studio.mandysa.music.ui.theme.textColor
import studio.mandysa.music.ui.theme.textColorLight
import studio.mandysa.music.ui.theme.verticalMargin

@Composable
fun ArtistItem(artist: SongBean.Local.Artist, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = horizontalMargin, vertical = verticalMargin)
                .size(50.dp), contentAlignment = Alignment.Center
        ) {
            AppRoundAsyncImage(modifier = Modifier.size(50.dp), url = "")
            // TODO: 歌手封面
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.0f)
                .padding(vertical = verticalMargin),
        ) {
            Text(
                text = artist.name,
                color = textColor,
                fontSize = 15.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = stringResource(id = R.string.total_n_songs, artist.songs.size),
                color = textColorLight,
                fontSize = 13.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
        /*Spacer(modifier = Modifier.padding(end = horizontalMargin))*/
    }
}

@Preview
@Composable
private fun PreviewArtistItem() {
    ArtistItem(artist = SongBean.Local.Artist(0, "啦啦啦", arrayListOf())) {

    }
}