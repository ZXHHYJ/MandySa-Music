package studio.mandysa.music.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import studio.mandysa.music.ui.theme.cornerShape
import studio.mandysa.music.ui.theme.dialogBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogCard(scope: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = cornerShape,
        colors = CardDefaults.cardColors(containerColor = dialogBackground)
    ) {
        scope.invoke()
    }
}