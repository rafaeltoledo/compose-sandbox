package net.rafaeltoledo.sandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import net.rafaeltoledo.sandbox.AppNavRoutes.USERS
import net.rafaeltoledo.sandbox.AppNavRoutes.USER_DETAILS
import net.rafaeltoledo.sandbox.AppNavRoutes.USER_DETAILS_ID_KEY
import net.rafaeltoledo.sandbox.ui.screen.userdetails.UserDetailsScreen
import net.rafaeltoledo.sandbox.ui.screen.users.UsersScreen
import net.rafaeltoledo.sandbox.ui.screen.users.RepositoriesViewModel
import net.rafaeltoledo.sandbox.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

  private val viewModel: RepositoriesViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      AppTheme {
        ProvideWindowInsets {
          SandboxApp(viewModel)
        }
      }
    }
  }
}

@Composable
fun SandboxApp(viewModel: RepositoriesViewModel) {
  val navController = rememberNavController()

  val actions = remember(navController) { AppNavActions(navController) }
  NavHost(navController, startDestination = USERS) {
    composable(USERS) {
      UsersScreen(
        viewModel = viewModel,
        selectUser = actions.selectUser,
      )
    }
    composable(
      "$USER_DETAILS/{$USER_DETAILS_ID_KEY}",
      arguments = listOf(
        navArgument(USER_DETAILS_ID_KEY) { type = NavType.LongType }
      ),
    ) {
      val arguments = requireNotNull(it.arguments)
      UserDetailsScreen(
        userId = arguments.getLong(USER_DETAILS_ID_KEY),
        upPress = actions.upPress
      )
    }
  }
}

object AppNavRoutes {
  const val USERS = "/users"
  const val USER_DETAILS = "/user"
  const val USER_DETAILS_ID_KEY = "userId"
}

class AppNavActions(navController: NavHostController) {
  val selectUser: (Long) -> Unit = { userId: Long ->
    navController.navigate("$USER_DETAILS/$userId")
  }

  val upPress: () -> Unit = {
    navController.navigateUp()
  }
}
