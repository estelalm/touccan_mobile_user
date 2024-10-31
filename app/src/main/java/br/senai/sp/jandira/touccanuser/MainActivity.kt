package br.senai.sp.jandira.touccanuser

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.edit
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.model.UserId
import br.senai.sp.jandira.touccanuser.screens.BicoDetails
import br.senai.sp.jandira.touccanuser.screens.ClientProfile
import br.senai.sp.jandira.touccanuser.screens.Home
import br.senai.sp.jandira.touccanuser.screens.Login
import br.senai.sp.jandira.touccanuser.screens.SetPassword
import br.senai.sp.jandira.touccanuser.screens.SignUpScreen
import br.senai.sp.jandira.touccanuser.screens.UserProfile
import br.senai.sp.jandira.touccanuser.screens.dataStore
import br.senai.sp.jandira.touccanuser.screens.user_id
import br.senai.sp.jandira.touccanuser.ui.theme.TouccanUserTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
                        startDestination = "logIn") {

                        composable(route = "signUp"){ SignUpScreen(navController)}

                        composable(route = "setPassword/{dados}",
                            arguments = listOf(
                                navArgument("dados") {
//                                type = NavType.StringType
                                },
                            )
                        ){ backStackEntry ->
                            val dadosJson = backStackEntry.arguments?.getString("dados")
                            Log.i("Dados: ", dadosJson.toString())
                            val user = Json.decodeFromString<User>(dadosJson ?: "")
                            SetPassword(navController, user, this@MainActivity) }

                        composable(route = "logIn"){ Login(navController, this@MainActivity) }

                        composable(route = "home/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val userId = backStackEntry.arguments?.getString("id")
                            val idUser = Json.decodeFromString<UserId>(userId ?: "")

                            fun main() = runBlocking { // this: CoroutineScope
                                launch { // launch a new coroutine and continue
                                    saveId(this@MainActivity, idUser.id)
                                }

                            }

                            Home(navController, idUser, this@MainActivity) }

                        composable(route = "perfilCliente/{id}", arguments = listOf(navArgument("id"){

                        })
                        ){ backStackEntry ->
                            val clientId = backStackEntry.arguments?.getString("id")
                            Log.i("Client: ", clientId.toString())
                            if (clientId != null) {
                                ClientProfile(navController, clientId, this@MainActivity )
                            }
                        }

                        composable(route = "bico/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val bicoId = backStackEntry.arguments?.getString("id")
                            if (bicoId != null) {
                                BicoDetails(navController, bicoId, this@MainActivity)
                            }

                        }

                        composable(route = "perfilUsuario/{id}", arguments = listOf(navArgument("id"){

                        })
                        ){backStackEntry->
                            val userId = backStackEntry.arguments?.getString("id")
                            if (userId != null){
                                UserProfile(navController, userId, this@MainActivity)
                            }
                        }

                        }
                    }

                }
            }
        }
    }



suspend fun saveId (context: Context, id: Int){
    context.dataStore.edit { settings ->
        settings[user_id] = id
    }
}
