package com.example.proyectokn
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectokn.ui.theme.ProyectoKNTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoKNTheme {
                MyApp()
            }
        }
    }
}
@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}
@Composable
fun Greetings(names : List<String> = List(15) {"$it"}){
    Surface(color = MaterialTheme.colors.background){
        Column(modifier = Modifier.padding(vertical = 4.dp)){
            LazyColumn{
                item{ Text("\t\tBarra del Nav")}
                items(names){contador->
                    Greeting(contador)
                }

            }
        }
    }
}
@Composable
private fun Greeting(contador: String) {
    val variable1 = remember { mutableStateOf(false) }
    val variable2 by animateDpAsState (
        targetValue = if (variable1.value) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column (
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(bottom = variable2)) {
                Text(text = "hola mundo.")
                Text(text = "| $contador |")
            }
            OutlinedButton(onClick = {variable1.value =!variable1.value}) {
                Text(if(variable1.value) "menos info" else "mas info")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: ()-> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenidos a mi app")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continuar")
            }
        }
    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ProyectoKNTheme {
        OnboardingScreen(onContinueClicked={})
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ProyectoKNTheme {
        MyApp()
    }
}
