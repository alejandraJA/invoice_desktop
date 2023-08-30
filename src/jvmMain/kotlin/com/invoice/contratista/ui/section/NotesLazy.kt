package com.invoice.contratista.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.invoice.contratista.data.source.web.models.Note

@Composable
fun NotesLazy(noteEntities: List<Note>, weight: Modifier) = LazyColumn(
    modifier = weight.padding(end = 8.dp, top = 8.dp, start = 8.dp),
    verticalArrangement = Arrangement.spacedBy(4.dp),
) {
    items(
        count = noteEntities.size,
    ) {
        ElevatedCard {
            Text(
                noteEntities[it].note,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp).fillMaxWidth()
            )
        }
    }
}