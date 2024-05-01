package com.arch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.arch.app.ui.theme.CLEAN_MVVM_Dagger_TDDTheme
import com.arch.app.vm.MainActivityViewModel
import com.arch.domain.model.ToDoData
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            CLEAN_MVVM_Dagger_TDDTheme {

                val success = viewModel.success.collectAsState()
                val isLoading = viewModel.isLoading.collectAsState()
                val error = viewModel.error.collectAsState(initial = 1)



                if (success.value == null) {
                    DefaultFirstUI()
                }
                if (success.value != null) {
                    SuccessUI(success.value)
                } else if (isLoading.value) {
                    LoadingUI()
                } else if (error.value != null) {
                    ErrorUI()
                }

                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                    )
                }*/


            }
        }
    }


    @Composable
    fun LoadingUI() {
            CircularProgressIndicator()
    }

    @Composable
    fun DefaultFirstUI() {

        var note by remember {
            mutableStateOf("")
        }

        val scope = rememberCoroutineScope()

        TextField(value = note, onValueChange = { note = it })

        Button(onClick = {
            scope.launch {
                viewModel.addNote(note)
            }
        }) {
            Text(text = "Save Note")

        }
    }

    @Composable
    fun SuccessUI(success: ToDoData?) {
        Text(text = success?.note ?: "")
    }

    @Composable
    fun ErrorUI() {

    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CLEAN_MVVM_Dagger_TDDTheme {
            Greeting("Android")
        }
    }
}