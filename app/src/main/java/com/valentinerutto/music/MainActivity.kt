package com.valentinerutto.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.valentinerutto.music.ui.AlbumScreen
import com.valentinerutto.music.ui.theme.MusicTheme
import com.valentinerutto.music.util.mToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val albumViewModel: AlbumsViewModel by viewModel()
    override fun onStart() {
        super.onStart()
        albumViewModel.fetchAlbumsList()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MusicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {


                    Scaffold(topBar = {
                        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
                    }) { contentPadding ->
                        Box(modifier = Modifier.padding(contentPadding)) {
                            AlbumScreen(albumViewModel = albumViewModel,
                                modifier = Modifier.fillMaxSize(),

                                onAlbumSelected = { itemPosition ->
                                    mToast(this@MainActivity, "Album $itemPosition")
                                })
                        }
                    }
                }

            }

        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicTheme {
        Greeting(name = "TEdX")
    }
}

const val url =
    "https://images.unsplash.com/photo-1521676129211-b7a9e7592e65?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
