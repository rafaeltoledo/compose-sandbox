package net.rafaeltoledo.sandbox.ui.screen.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
  selectUser: (Long) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: RepositoriesViewModel = viewModel(),
) {
  val state = viewModel.state.collectAsState().value

  Scaffold(
    modifier = modifier
      .navigationBarsPadding()
      .statusBarsPadding(),
  ) { paddingValues ->
    if (state.isLoading) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator()
      }
    } else {
      LazyColumn(
        content = {
          items(state.users) { user ->
            ListItem(
              modifier = Modifier.clickable { selectUser(user.id) },
              headlineText = {
                Text(
                  HtmlCompat.fromHtml(
                    user.displayName,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                  ).toString()
                )
              },
              supportingText = {
                Text(
                  HtmlCompat.fromHtml(
                    user.location ?: "",
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                  ).toString()
                )
              },
              leadingContent = {
                AsyncImage(
                  model = ImageRequest.Builder(LocalContext.current)
                    .data(user.profileImage)
                    .crossfade(true)
                    .build(),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier.clip(CircleShape)
                    .width(48.dp)
                    .height(48.dp),
                )
              }
            )
          }
        },
      )
    }
  }
}
