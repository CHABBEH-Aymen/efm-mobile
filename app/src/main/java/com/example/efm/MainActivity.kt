package com.example.efm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.efm.models.TaskViewModel
import com.example.efm.ui.CreateDialog
import com.example.efm.ui.TaskItem
import com.example.efm.ui.theme.EfmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = TaskViewModel()
        enableEdgeToEdge()
        setContent {
            EfmTheme {
                var onEdit by remember { mutableStateOf(false) }
                var onCreate by remember { mutableStateOf(false) }
                val tasks by viewModel.tasks.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
                    FloatingActionButton(onClick = {onCreate = true}, containerColor = MaterialTheme.colorScheme.primary) {
                        Icon(Icons.Default.Add, contentDescription = "Add Task")
                    }
                }) { innerPadding ->

                    if(onCreate)
                        CreateDialog(onCreate = {task ->
                            task.id = tasks.size+1
                            viewModel.createTask(task)


                    }, onDismiss = {onCreate = false})

                    LazyColumn (Modifier.padding(innerPadding))
                    {
                        items(tasks){ task ->
                            TaskItem(task = task,
                                onDelete = {
                                viewModel.destroyTask(task.id!!)
                            },
                                onUpdate = {
                                    onEdit = true;
                                })
                        }
                    }
                }
            }
        }
    }
}
