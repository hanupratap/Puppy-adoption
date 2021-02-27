package com.example.androiddevchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.fragment.findNavController
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage

class PuppiesList :Fragment() {
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    MyPuppies()
                }
            }
        }
    }

    // Start building your app here!
    @Composable
    fun MyPuppies() {
        val animals by viewModel.livedogs.observeAsState(initial = emptyList())

        Surface(color = MaterialTheme.colors.background) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(
                        text = "Puppies",
                        modifier = Modifier.padding(16.dp).height(10.dp),
                        style = typography.h2
                    )
                }
                items(items = animals, itemContent = {
                    MyCard(dog = it)
                })
            }
        }
    }

    @Composable
    fun MyCard(dog: Dog){
        Box(modifier = Modifier.padding(10.dp)){
            Column {
                CoilImage(
                    data = dog.image_url,
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
                        .clip(shape = RoundedCornerShape(5.dp))
                        .clickable {
                            val bundle = Bundle()
                            bundle.putInt("puppy_id", dog.id)
                            findNavController().navigate(
                                R.id.action_puppiesList_to_puppyDetail,
                                bundle
                            )
                        },
                    contentScale = ContentScale.Crop,
                )
                Text(text = "Hello, My name is ${dog.name}",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    style = typography.h6
                )
                Text(text = "Hello, My name is ${dog.description}",
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 12.dp)
                        .fillMaxWidth(),
                    style = typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }


    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyPuppies()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyPuppies()
        }
    }

}