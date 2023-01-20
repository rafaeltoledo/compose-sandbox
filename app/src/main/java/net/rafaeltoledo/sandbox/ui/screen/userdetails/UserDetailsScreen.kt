package net.rafaeltoledo.sandbox.ui.screen.userdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun UserDetailsScreen(userId: Long, upPress: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(modifier = Modifier.statusBarsPadding()) {
        IconButton(
          onClick = { upPress.invoke() },
          modifier = Modifier.align(Alignment.CenterVertically)
        ) {
          Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
          )
        }
        Text(
          "$userId"
        )
      }
    }
  ) {
    Box(modifier = Modifier.padding(it))
  }
}
