package ngoding.modol.cleanote.ui.notelist

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ngoding.modol.cleanote.ui.theme.CleanoteTheme

@Composable
fun NoteListItem(
    note: NoteListScreenState.Note,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    var menuExpanded by remember { mutableStateOf(false) }
    ListItem(
        headlineContent = {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        trailingContent = {
            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = { Icon(Icons.Default.Edit, null) },
                            text = { Text("Edit") },
                            onClick = {
                                menuExpanded = false
                                onEdit()
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = { Icon(Icons.Default.Delete, null) },
                            text = { Text("Delete") },
                            onClick = {
                                menuExpanded = false
                                onDelete()
                            }
                        )
                    }
                }
            }
        },
        modifier = Modifier.clickable(onClick = onEdit)
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun NoteListItemPreview() {
    CleanoteTheme {
        NoteListItem(
            note = NoteListScreenState.Note(
                id = 0,
                title = "Note Title 1",
                body = "Note Body 1"
            ),
            onEdit = {},
            onDelete = {}
        )
    }
}