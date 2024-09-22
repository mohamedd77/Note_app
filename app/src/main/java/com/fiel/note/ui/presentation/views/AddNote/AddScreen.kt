package com.fiel.note.ui.presentation.views.AddNote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavHostController, viewModel: AddViewModel = hiltViewModel()) {

    // Color Palette
    val backgroundColor = Color(0xFFF5F5F5)
    val topBarColor = Color(0xFF6200EA)
    val buttonColor = Color(0xFFFFC107)
    val textColor = Color(0xFF37474F)
    val textFieldBorderColor = Color(0xFF03A9F4)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                    }
                },
                title = {
                    Text(text = "New Note", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = topBarColor
                )
            )
        },
        modifier = Modifier.background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = "Title", color = textColor) },
                value = viewModel.titulo,
                onValueChange = {
                    viewModel.titulo = it
                },
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = textFieldBorderColor,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = textFieldBorderColor,
                    focusedLabelColor = textFieldBorderColor,
                    unfocusedLabelColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = "Content", color = textColor) },
                value = viewModel.contenido,
                onValueChange = {
                    viewModel.contenido = it
                },
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = textFieldBorderColor,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = textFieldBorderColor,
                    focusedLabelColor = textFieldBorderColor,
                    unfocusedLabelColor = Color.Gray
                )

            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.addNote()
                    navController.popBackStack()
                },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "ADD NOTE")
            }
        }
    }
}
