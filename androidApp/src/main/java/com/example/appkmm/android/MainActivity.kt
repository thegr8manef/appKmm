package com.example.appkmm.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appkmm.src.repository.CorelibDelegate


class MainActivity : ComponentActivity() {
     val TAG = "MainAct"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val repository = CorelibDelegate.initCoreLib()
                    val state = remember { mutableStateOf("Loading") }
                    //val dataStoreManager = AndroidDataStoreManager(applicationContext)
                    //val dataManager = DataManager(dataStoreManager)
                    LaunchedEffect(true) {
                        try {
                            state.value = repository.postUserData(applicationContext).toString()
                            //repository.saveUserData(applicationContext)
                            Log.w(TAG,"the saved data is : ${repository.readUserData(applicationContext)}")
                        } catch (e: Exception) {
                            state.value
                        }
                    }
                    GreetingView(state.value)
                    Log.w(TAG,state.value)

                }
            }




        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
