package com.example.efm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.efm.data.Task

@Composable
fun TaskItem(modifier: Modifier = Modifier, task: Task, onDelete: () -> Unit = {}, onUpdate: (task: Task) -> Unit = {})
{
    var bgColor = Color.Cyan
    if (task.completed) bgColor = Color.Green
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(bgColor),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp).background(Color.Transparent))
        {
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth() // This will make the Row fill the width
                    .padding(top = 8.dp)
                    .background(Color.Transparent), // Add some space between text and buttons
                horizontalArrangement = Arrangement.SpaceBetween // Distribute the buttons evenly
            ) {
                // Delete Button
                Button(
                    modifier = Modifier.background(Color.Red),
                    onClick = { onDelete() },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, // You can use a delete icon here
                        contentDescription = "Delete"
                    )
                    Text(text = "Delete", style = MaterialTheme.typography.bodySmall)
                }

                // Edit Button
                Button(
                    modifier = Modifier.background(Color.Gray),
                    onClick = { onUpdate(task) },
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit, // You can use an edit icon here
                        contentDescription = "Edit"
                    )
                    Text(text = "Edit", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}