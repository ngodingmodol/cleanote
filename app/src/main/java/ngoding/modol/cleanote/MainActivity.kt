package ngoding.modol.cleanote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ngoding.modol.cleanote.ui.notedetail.navigateToNoteDetail
import ngoding.modol.cleanote.ui.notedetail.noteDetailScreenRoute
import ngoding.modol.cleanote.ui.notelist.NoteListRoute
import ngoding.modol.cleanote.ui.notelist.noteListScreenRoute
import ngoding.modol.cleanote.ui.theme.CleanoteTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanoteTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NoteListRoute
                ) {
                    noteListScreenRoute(
                        onNavigateToNoteDetail = navController::navigateToNoteDetail
                    )
                    noteDetailScreenRoute(
                        onNavigateBack = navController::popBackStack
                    )
                }
            }
        }
    }
}