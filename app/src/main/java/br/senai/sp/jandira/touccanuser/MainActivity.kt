package br.senai.sp.jandira.touccanuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.screens.Login
import br.senai.sp.jandira.touccanuser.screens.SetPassword
import br.senai.sp.jandira.touccanuser.screens.SignUpScreen
import br.senai.sp.jandira.touccanuser.screens.SetPassword
import br.senai.sp.jandira.touccanuser.screens.SignUpScreen
import br.senai.sp.jandira.touccanuser.screens.Login
import br.senai.sp.jandira.touccanuser.ui.theme.TouccanUserTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TouccanUserTheme {
                Surface(color = Color(0xffEBEBEB)) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "signUp") {
                        composable(route = "signUp"){ SignUpScreen(navController)}
                        composable(route = "setPassword/{dados}",
                            arguments = listOf(navArgument("dados") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->
                            val dadosJson = backStackEntry.arguments?.getString("dados")
                            val user = Json.decodeFromString<User>(dadosJson ?: "")
                            SetPassword(navController, user, this@MainActivity) }
                        composable(route = "logIn"){ Login(navController) }
                    }
                }
            }
        }
    }
}



//    @Preview(showBackground = true, showSystemUi = true)
//    @Composable
//    fun SignUpScreenPreview() {
//        TouccanMobileUserTheme {
//            SignUpScreen()
//        }
//    }
//}