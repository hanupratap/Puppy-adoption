package com.example.androiddevchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Pets
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.data.MainActivityViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PuppiesList :Fragment() {
    private lateinit var viewModel: MainActivityViewModel


    @ExperimentalFoundationApi
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

    @Composable
    fun FirstCard(){

        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Cyan,
            animationSpec = infiniteRepeatable(
                animation = tween(4000, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Card(
            elevation = 20.dp,
            modifier = Modifier
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .background(color = color)
                    .height(150.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = "Hello there,",
                    style = typography.h5,
                    fontFamily = FontFamily.Monospace
                )

                Text(text = "Upgrade to premium and get exclusive access to more DAWGS")
                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(50)
                ) {

                    Text(
                        text = "Become Premium",
                        style = typography.subtitle2,
                    )
                }

            }
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
    }
    
    // Start building your app here!
    @ExperimentalFoundationApi
    @Composable
    fun MyPuppies() {
        val animals by viewModel.livedogs.observeAsState(initial = emptyList())

        Surface(color = MaterialTheme.colors.background) {

                LazyColumn(
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    item {
                        MyHeader()
                    }
                    item { FirstCard() }
                    item{
                        Card(
                            elevation = 20.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {

                        }
                    }

                    items(items = animals, itemContent = {
                        MyCard(dog = it)
                    },

                        )
                    item {
                        Spacer(modifier = Modifier.padding(7.dp))
                    }
                }

        }
    }

    @Composable
    fun MyHeader(){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Puppies",
                style = typography.h3,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
                color = MaterialTheme.colors.secondaryVariant
            )
            Icon(imageVector = Icons.TwoTone.Pets, contentDescription = "Puppy age icon")
        }
    }

    @Composable
    fun MyCard(dog: Dog){
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Card(
                    elevation = 10.dp,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .clickable {
                            val bundle = Bundle()
                            bundle.putInt("puppy_id", dog.id)
                            findNavController().navigate(
                                R.id.action_puppiesList_to_puppyDetail,
                                bundle
                            )
                        }
                )
                {
                    CoilImage(
                        data = dog.image_url,
                        contentDescription = null,
                        fadeIn = true,
                        loading = {
                            Box(Modifier.matchParentSize()) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        },
                        contentScale = ContentScale.Crop,

                        )
                }

                Spacer(Modifier.height(16.dp))

                Text(text = dog.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = typography.h6
                )
                Spacer(Modifier.height(4.dp))
                Text(text = dog.short_description,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = typography.body1
                )
                Spacer(Modifier.height(4.dp))
                if(dog.age!=0){
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        item{
                            NormalChip(text = "${dog.age} years")
                        }
                        item{
                            NormalChip(text = if(dog.gender) "Female" else "Male")
                        }
                        item{
                            NormalChip(text = dog.breed)
                        }


                    }

                }
                else{
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        item{
                            Chip(text = "New Born")
                        }
                        item{
                            NormalChip(text = if(dog.gender) "Female" else "Male")
                        }
                        item{
                            NormalChip(text = dog.breed)
                        }

                    }

                }
            }
    }

    @Composable
    fun NormalChip(text: String){
        Text(text = text,
            modifier = Modifier
                .border(
                    1.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = RoundedCornerShape(50)
                )
                .padding(vertical = 0.dp, horizontal = 12.dp),
            style = typography.subtitle2,
        )
    }

    @Composable
    fun Chip(modifier: Modifier = Modifier, text: String) {
        val value by animateFloatAsState(
            targetValue = 1f,
            animationSpec = repeatable(
                iterations = 3,
                animation = tween(durationMillis = 300),
                repeatMode = RepeatMode.Reverse
            )
        )

        Card(
            modifier = modifier,
            elevation = 0.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp, 16.dp)
                        .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
                        .alpha(value)
                )
                Spacer(Modifier.width(4.dp))
                Text(text = text,
                    style = typography.subtitle2,
                )
            }
        }
    }


    @ExperimentalFoundationApi
    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyPuppies()
        }
    }

    @ExperimentalFoundationApi
    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyPuppies()
        }
    }

}