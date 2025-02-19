package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {

    var result by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    when (result) {
        1 -> {
            LemonadeTextApp(
                textResourceId = R.string.lemon_tree,
                imageResourceId = R.drawable.lemon_tree,
                contentResourceId = R.string.lemon_tree_description,
                onImageClick = {
                    result = 2
                    squeezeCount = (2..4).random()

                }
            )
        }


        2 -> {
            LemonadeTextApp(
                textResourceId = R.string.lemon,
                imageResourceId = R.drawable.lemon_squeeze,
                contentResourceId = R.string.lemon_description,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        result = 3
                    }
                }
            )
        }

        3 -> {
            LemonadeTextApp(
                textResourceId = R.string.lemon_glass,
                imageResourceId = R.drawable.lemon_drink,
                contentResourceId = R.string.lemon_glass_description,
                onImageClick = {
                    result = 4

                }
            )
        }

        4 -> {
            LemonadeTextApp(
                textResourceId = R.string.empty_glass,
                imageResourceId = R.drawable.lemon_restart,
                contentResourceId = R.string.empty_glass_description,
                onImageClick = {
                    result = 1

                }
            )
        }


    }

}

@Composable
fun LemonadeTextApp(
    textResourceId: Int,
    imageResourceId: Int,
    contentResourceId: Int,
    onImageClick: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ){
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.size(180.dp)
        ) {

            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(contentResourceId),
                modifier = Modifier.clickable (onClick = onImageClick)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResourceId) //The text Below

        )

    }

}

@Preview(showBackground = true, showSystemUi = true)

@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}