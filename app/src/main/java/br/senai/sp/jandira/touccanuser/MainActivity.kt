package br.senai.sp.jandira.touccanuser

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.screens.Avaliacao
import br.senai.sp.jandira.touccanuser.screens.BicoDetails
import br.senai.sp.jandira.touccanuser.screens.Chat
import br.senai.sp.jandira.touccanuser.screens.ChatList
import br.senai.sp.jandira.touccanuser.screens.ClientProfile
import br.senai.sp.jandira.touccanuser.screens.Cofre
import br.senai.sp.jandira.touccanuser.screens.Denuncia
import br.senai.sp.jandira.touccanuser.screens.History
import br.senai.sp.jandira.touccanuser.screens.Home
import br.senai.sp.jandira.touccanuser.screens.Login
import br.senai.sp.jandira.touccanuser.screens.MeuBicoDetails
import br.senai.sp.jandira.touccanuser.screens.MeuCartao
import br.senai.sp.jandira.touccanuser.screens.SetPassword
import br.senai.sp.jandira.touccanuser.screens.Settings
import br.senai.sp.jandira.touccanuser.screens.SignUpScreen
import br.senai.sp.jandira.touccanuser.screens.UserProfile
import br.senai.sp.jandira.touccanuser.ui.theme.TouccanUserTheme
import kotlinx.serialization.json.Json
import android.Manifest
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.collectAsState
import br.senai.sp.jandira.touccanuser.screens.Conta
import br.senai.sp.jandira.touccanuser.screens.Notifications
import br.senai.sp.jandira.touccanuser.screens.SearchScreen
import br.senai.sp.jandira.touccanuser.screens.Seguranca
import br.senai.sp.jandira.touccanuser.screens.SobreNos
import br.senai.sp.jandira.touccanuser.screens.Suporte
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.messaging


class MainActivity : ComponentActivity() {
    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        askNotificationPermission()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        setContent {
            TouccanUserTheme {
                Surface(color = Color(0xffEBEBEB)) {
                    val navController = rememberNavController()

                    val userPreferences = UserPreferences(this)
                    val userIdFlow = userPreferences.userId.collectAsState(initial = 0)

                    NavHost(
                        navController = navController,
                        startDestination = if(userIdFlow.value== 0 || userIdFlow.value == null){"logIn"} else{"home/${userIdFlow.value}"}) {

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

                        composable(route = "logIn"){

                            val userPreferences = UserPreferences(this@MainActivity)
                            Login(navController, this@MainActivity, userPreferences) }

                        composable(route = "home/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val userId = backStackEntry.arguments?.getString("id")?.toInt()

                            Home(navController, userId, this@MainActivity) }

                        composable(route = "perfilCliente/{id}", arguments = listOf(navArgument("id"){

                        })
                        ){ backStackEntry ->
                            val clientId = backStackEntry.arguments?.getString("id")
                            Log.i("Client: ", clientId.toString())
                            if (clientId != null) {
                                ClientProfile(navController, clientId, this@MainActivity )
                            }
                        }

                        composable(route = "bico/{id}/{candidatou}",
                            arguments = listOf( navArgument("id") { type = NavType.StringType },
                                                navArgument("candidatou") { type = NavType.BoolType }
                            )
                        ){ backStackEntry ->

                            val bicoId = backStackEntry.arguments?.getString("id")
                            val candidatado = backStackEntry.arguments?.getBoolean("candidatou") ?: false

                            if (bicoId != null) {
                                BicoDetails(navController, bicoId, this@MainActivity,candidatado)
                            }

                        }

                        composable(route = "meuBico/{id}/{candidatou}",
                            arguments = listOf( navArgument("id") { type = NavType.StringType },
                                navArgument("candidatou") { type = NavType.BoolType }
                            )
                        ){ backStackEntry ->

                            val bicoId = backStackEntry.arguments?.getString("id")
                            val candidatado = backStackEntry.arguments?.getBoolean("candidatou") ?: false

                            if (bicoId != null) {
                                MeuBicoDetails(navController, bicoId, this@MainActivity,candidatado)
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

                        composable(route = "historico/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val userId = backStackEntry.arguments?.getString("id")?.toInt()
                            if (userId != null) {
                                History(navController, userId, this@MainActivity)
                            }
                        }

                        composable(route = "avaliacao/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val bicoId = backStackEntry.arguments?.getString("id")
                            if (bicoId != null) {
                                Avaliacao(navController, bicoId, this@MainActivity)
                            }
                        }

                        composable(route = "denuncia/{id}/{cliente}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            }, navArgument("cliente") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val bicoId = backStackEntry.arguments?.getString("id")
                            val clienteId = backStackEntry.arguments?.getString("cliente")
                            if (bicoId != null) {
                                Denuncia(navController, bicoId, this@MainActivity, clienteId)
                            }
                        }

                        composable(route = "cartao/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->

                            val userId = backStackEntry.arguments?.getString("id")

                            MeuCartao(navController, userId, this@MainActivity) }

                        composable(route = "cofrinho",
                        ){ backStackEntry ->
                                Cofre(navController, this@MainActivity)
                        }
                        composable(route = "pesquisa",
                        ){ backStackEntry ->
                            SearchScreen(navController, this@MainActivity)
                        }


                        composable(route = "configuracoes",
                        ){ backStackEntry ->
                            Settings(navController, this@MainActivity)
                        }
                        composable(route = "sobrenos",
                        ){ backStackEntry ->
                            SobreNos(navController, this@MainActivity)
                        }
                        composable(route = "seguranca",
                        ){ backStackEntry ->
                            Seguranca(navController, this@MainActivity)
                        }
                        composable(route = "conta",
                        ){ backStackEntry ->
                            Conta(navController, this@MainActivity)
                        }
                        composable(route = "suporte",
                        ){ backStackEntry ->
                            Suporte(navController, this@MainActivity)
                        }

                        composable(route = "chatList",
                        ){ backStackEntry ->
                            ChatList(navController, this@MainActivity)
                        }
                        composable(route = "chat/{id}",
                            arguments = listOf(navArgument("id") {
//                                type = NavType.StringType
                            })
                        ){ backStackEntry ->
                            val clientId = backStackEntry.arguments?.getString("id")
                            Chat(navController, clientId, this@MainActivity)
                        }
                        composable(route = "notificacoes",
                        ){ backStackEntry ->
                            Notifications(navController, this@MainActivity)
                        }

                        }
                    }

                }
            }
        Firebase.messaging.isAutoInitEnabled = true
        }



    }



