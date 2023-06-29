package com.vadimko.food2workkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vadimko.food2workkmm.android.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

//const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
//const val BASE_URL = "https://food2fork.ca/api/recipe"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*       val ktorClient = KtorClientFactory().build()
               CoroutineScope(IO).launch {
                   val recipeService = RecipeServiceImpl(
                       httpClient = ktorClient,
                       baseUrl = BASE_URL
                   )
                   val recipeId = 666
                   val recipe = recipeService.get(recipeId)
                   Log.wtf("response", "${recipe.dateAdded}  ${recipe.title}  ${recipe.featuredImage}")
               }*/
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}



@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
    }
}
