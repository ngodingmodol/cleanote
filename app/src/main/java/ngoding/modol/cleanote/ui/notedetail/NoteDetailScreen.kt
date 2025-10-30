package ngoding.modol.cleanote.ui.notedetail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ngoding.modol.cleanote.ui.theme.CleanoteTheme

@Composable
fun NoteDetailScreen(
    state: NoteDetailScreenState,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit,
    onSaveClick: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TextField(
                value = state.title,
                onValueChange = onTitleChange,
                placeholder = {
                    Text("Title")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                value = state.body,
                onValueChange = onBodyChange,
                placeholder = {
                    Text("Body")
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Button(onClick = {
                onSaveClick()
            }) {
                Text(
                    text = "Save",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoteDetailPreview() {
    CleanoteTheme {
        NoteDetailScreen(
            state = NoteDetailScreenState(
                title = "Ngoding",
                body = "Modol"
            ),
            onTitleChange = {},
            onBodyChange = {},
            onSaveClick = {}
        )
    }
}