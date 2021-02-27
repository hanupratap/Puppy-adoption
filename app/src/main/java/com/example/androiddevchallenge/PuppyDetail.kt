package com.example.androiddevchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PuppyDetail : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private var puppyID: MutableState<Int> = mutableStateOf(-1)
    private var dog:Dog ? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)


        CoroutineScope(Main).launch {
            delay(1000)
            arguments?.getInt("puppy_id")?.let{
                puppyID.value = it
            }

            dog = viewModel.getDogByID(puppyID.value)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    MyPuppy()
                }
            }
        }
    }

    @Composable
    fun MyDog(){
            Box(modifier = Modifier.padding(10.dp)){
                Column {
                    dog?.let {
                        CoilImage(
                            data = it.image_url,
                            contentDescription = "This is image",
                            fadeIn = true,
                            loading = {
                                Box(Modifier.matchParentSize()) {
                                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                                }
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .height(180.dp)
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(5.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Text(text = "Hello, My name is ${dog?.name}",
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        style = typography.h6
                    )
                    Text(text = "Hello, My name is ${dog?.description}",
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 12.dp)
                            .fillMaxWidth(),
                        style = typography.body1,

                        overflow = TextOverflow.Ellipsis)
                }
            }

    }

    @Composable
    fun MyPuppy(){

        Surface(color = MaterialTheme.colors.background) {
            when(this.puppyID.value != -1 && this.dog != null) {
                true -> {
                    MyDog()
                }
                false -> Text(text = "Loading...")
            }

        }

    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyPuppy()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyPuppy()
        }
    }
}