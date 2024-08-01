package com.example.portfolioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolioapp.ui.theme.PortfolioAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortfolioScreen()
                }
            }
        }
    }
}

@Composable
fun PortfolioScreen()
{
    Surface(
        modifier = Modifier.fillMaxSize(),

        ) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {
            //we've created a column inside card
            Column (//whatever we apply here will be applied to child of column
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                ProfileImage()
                HorizontalDivider(thickness = 2.dp)
                CreateInfo()
                //Divider(thickness = 2.dp)
                CreateButton()
            }
        }
    }
}

@Composable
private fun CreateButton() {
    //Below is not an ordinary variable it is a special kind of variable which
    //wll keep track of the button event whether it is clicked or not
    val buttonClickedEvent = remember {
        mutableStateOf(false)
    }

    Button(
        modifier = Modifier
            .padding(3.dp)
            .height(60.dp)
            .width(150.dp),
        shape = RoundedCornerShape(
            topStart = 10.dp, topEnd = 10.dp,
            bottomStart = 10.dp, bottomEnd = 10.dp
        ),
        colors = ButtonDefaults.buttonColors(Color.Blue),
        onClick = {
            //we will compliment the special variable value every time
            // the button is clicked
            buttonClickedEvent.value = !buttonClickedEvent.value
        },
    ) {
        Text(
            text = "Portfolio",
            fontSize = 25.sp
        )
    }
    //what UI element generated from below code is shown exactly below
    //the button
    if (buttonClickedEvent.value)
        Content()
    else
        Box {}
}
@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Miles P.         ",
            color = Color.Blue,
            fontSize = 50.sp
        )
        Text(
            text = "Android Compose Programmer",
            fontSize = 20.sp
        )
        Text(
            text = "@themileesCompose                   ",
            fontSize = 20.sp,
        )
    }
}

@Composable
private fun ProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(200.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(
            topStart = 250.dp,
            topEnd = 250.dp,
            bottomStart = 250.dp,
            bottomEnd = 250.dp
        ),
        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        shadowElevation = 4.dp,
        border = BorderStroke(0.5.dp, Color.LightGray)
    )
    {
        //adding image
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Profile Image",
            modifier = Modifier.size(135.dp)
        )
    }
}
@Preview
@Composable
fun Content(modifier: Modifier = Modifier)
{
    Box (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Surface (modifier= Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(
                topStart = 15.dp, topEnd = 15.dp,
                bottomStart = 15.dp, bottomEnd = 15.dp),
            border = BorderStroke(3.dp, color = Color.Black)
        ){
            Surface (
                modifier= Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                Portfolio(data = listOf("Project 1", "Project 2", "Project 3"
                    ,"Project 1", "Project 2", "Project 3"))
            }
        }
    }
}
@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){ item ->
            Card (
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp),
                ){
                    ProfileImage(modifier = Modifier.size(100.dp))
                    Column (
                        modifier = Modifier
                            .padding(7.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ){
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great project",
                            style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
    PortfolioAppTheme {
        PortfolioScreen()
    }
}
