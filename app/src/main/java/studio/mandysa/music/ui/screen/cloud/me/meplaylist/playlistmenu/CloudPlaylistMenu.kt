package studio.mandysa.music.ui.screen.cloud.me.meplaylist.playlistmenu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.pop
import studio.mandysa.music.R
import studio.mandysa.music.ui.common.AppDialog
import studio.mandysa.music.ui.common.MenuItem
import studio.mandysa.music.ui.screen.DialogDestination
import studio.mandysa.music.ui.screen.ScreenDestination
import studio.mandysa.music.ui.theme.horizontalMargin

@Composable
fun CloudPlaylistMenu(
    mainNavController: NavController<ScreenDestination>,
    dialogNavController: NavController<DialogDestination>,
    id: String
) {
    AppDialog {
        LazyColumn(modifier = Modifier.padding(horizontal = horizontalMargin)) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                MenuItem(
                    title = stringResource(id = R.string.new_playlist),
                    imageVector = Icons.Rounded.Add
                ) {
                    dialogNavController.pop()
                }
            }
            item {
                MenuItem(
                    title = stringResource(id = R.string.remove_playlist),
                    imageVector = Icons.Rounded.Delete
                ) {
                    dialogNavController.pop()
                }
            }
        }
    }
}