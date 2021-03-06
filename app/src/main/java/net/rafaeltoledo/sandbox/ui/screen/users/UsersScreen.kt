package net.rafaeltoledo.sandbox.ui.screen.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersScreen(
  viewModel: RepositoriesViewModel = viewModel(),
  selectUser: (Long) -> Unit
) {
  val state = viewModel.state.collectAsState().value

  Scaffold(
    modifier = Modifier
      .navigationBarsPadding()
      .statusBarsPadding(),
  ) {
    if (state.isLoading) {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator()
      }
    } else {
      LazyColumn(
        content = {
          items(state.users) { user ->
            ListItem(
              Modifier.clickable {
                selectUser.invoke(user.id)
              },
              text = {
                Text(
                  HtmlCompat.fromHtml(
                    user.displayName,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                  ).toString()
                )
              },
              secondaryText = {
                Text(
                  HtmlCompat.fromHtml(
                    user.location ?: "",
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                  ).toString()
                )
              },
              icon = {
                  Image(
                    painter = rememberImagePainter(
                      data = user.profileImage,
                      builder = {
                        transformations(CircleCropTransformation())
                        crossfade(true)
                      },
                    ),
                    contentDescription = null,
                  )
              },
            )
          }
        },
      )
    }
  }
}
