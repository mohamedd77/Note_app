package com.fiel.note.ui.presentation.views.homeScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fiel.note.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())

    // Color Palette
    val backgroundColor = Color(0xFFF0F0F0)
    val topBarColor = Color(0xFF6200EA)
    val fabColor = Color(0xFFFFC107)
    val cardBorderColor = Color(0xFF03A9F4)
    val textColor = Color(0xFF37474F)
    val iconTint = Color(0xFF03A9F4)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Notes", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = topBarColor
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screens.AddScreen.route)
            }, containerColor = fabColor) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
            }
        },
        modifier = Modifier
            .background(backgroundColor) // Set background color for the whole screen
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(notes.value) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(1.dp, color = cardBorderColor)
                        .background(Color.White) // Card background color
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "#${notes.value.indexOf(it) + 1}",
                                color = textColor
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = {
                                navController.navigate(Screens.UpdateScreen.getById(it.id))
                            }) {
                                Icon(
                                    tint = iconTint,
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = ""
                                )
                            }
                            IconButton(onClick = {
                                viewModel.deleteNote(note = it)
                            }) {
                                Icon(
                                    tint = Color.Red.copy(0.7f),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = ""
                                )
                            }
                        }
                        Text(
                            text = it.titulo,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = it.contenido, color = textColor)
                    }
                }
            }
        }
    }
}
