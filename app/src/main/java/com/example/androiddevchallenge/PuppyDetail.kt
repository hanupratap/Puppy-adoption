package com.example.androiddevchallenge

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite

import androidx.compose.runtime.*

import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.androiddevchallenge.data.MainActivityViewModel
import com.example.androiddevchallenge.ui.theme.shapes


class PuppyDetail : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private var puppyID: MutableState<Int> = mutableStateOf(-1)
    private var dog:Dog ? = null
    private val START_TOP_PADDING = 320


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

    @ExperimentalAnimationApi
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
    fun Parallax() {


        val scrollState = rememberScrollState()
        val imageOffset = (-scrollState.value * 0.2f).dp
        val iconBackgroundAlpha = ((scrollState.value / START_TOP_PADDING.toFloat()) * 0.2f).coerceAtMost(0.2f)

        Box {
            dog?.let {
                CoilImage(
                    data = it.image_url,
                    contentDescription = null,
                    fadeIn = true,
                    loading = {
                        Box(Modifier.matchParentSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                    modifier = Modifier
                        .offset(y = imageOffset)
                        .height(370.dp)
                        .fillMaxWidth(),

                    contentScale = ContentScale.Crop,
                    )
            }

            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .padding(top = START_TOP_PADDING.dp)
                    .background(
                        MaterialTheme.colors.surface,
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(all = 32.dp)
            ) {
                Text("Hello, I am", style = MaterialTheme.typography.h6)
                dog?.let { Text(text = it.name, style = MaterialTheme.typography.h4) }



                dog?.let { Text(text = it.short_description, style = MaterialTheme.typography.caption) }
                Spacer(modifier = Modifier.size(5.dp))
                ExpandingFab()

                Spacer(modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.size(16.dp))

                dog?.let { Text(text = it.description, style = MaterialTheme.typography.body1) }

                Spacer(Modifier.size(16.dp))

             }
            IconButton(onClick = { activity?.onBackPressed() }, modifier = Modifier
                .padding(8.dp)
                .background(Color.Black.copy(alpha = iconBackgroundAlpha), shape = CircleShape)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color.White
                )
            }


        }


    }



    @Composable
    fun ExpandingFab(){

        var fav by remember {
            mutableStateOf(false)
        }

        ExtendedFloatingActionButton(text = {
            Text(text = "Favorite")
        },
            onClick = {
                fav = !fav
            },

            icon = {
                Icon(imageVector = Icons.Outlined.Favorite ,
                    ""
                )
            }
        )


    }

//    @Composable
//    fun MultiFloatingActionButton(
//        fabIcon: ImageBitmap,
//        toState: MultiFabState
//    ) {
//        var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
//        val transition = updateTransition(targetState = toState)
//        val rotation: Float by transition.animateFloat{ state ->
//            if (state == MultiFabState.EXPANDED) 45f else 0f
//        }
//
//    }



    @Composable
    fun LoadingAnim(){
        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = MaterialTheme.colors.primary,
            targetValue = MaterialTheme.colors.secondary,
            animationSpec = infiniteRepeatable(
                animation = tween(2500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = color, shape = CircleShape),

                )
            {
                Image(
                    painter = painterResource(id = R.drawable.dog_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }
        }



    }

    @ExperimentalAnimationApi
    @Composable
    fun MyPuppy(){
        Surface(color = MaterialTheme.colors.background) {

            Crossfade(
                targetState = this.puppyID.value != -1 && this.dog != null,

            ) { screen ->
                when (screen) {
                    true -> Parallax()
                    false -> LoadingAnim()
                }
            }

        }

    }

    @ExperimentalAnimationApi
    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyPuppy()
        }
    }

    @ExperimentalAnimationApi
    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyPuppy()
        }
    }
}
