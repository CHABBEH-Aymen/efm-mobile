package com.example.efm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.efm.data.Task

@Composable
fun CreateDialog(modifier: Modifier = Modifier, onCreate: (task: Task)->Unit, onDismiss: ()->Unit) {
    var title = remember { mutableStateOf("") }
    var completed = remember { mutableStateOf("") }
    var priority = remember { mutableStateOf("Priority") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Add New Task", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Task Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                SimpleSelectMenu(listOf("Height", "Medium", "Low"), priority.value, {
                    priority.value = it
                })
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (title.value.isNotBlank()) {
                                onCreate(
                                    Task(
                                        id = null,
                                        title = title.value,
                                        completed = completed.value == "true",
                                        priority = priority.value
                                    )
                                )
                                onDismiss()
                            }
                        }
                    ) {
                        Text("Add")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            }
        }

    }
}

