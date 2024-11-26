package br.senai.sp.jandira.touccanuser.utility

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.UserId
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavHostController, context: Context) {

    var userId = remember { mutableStateOf("") }

    val userPreferences = UserPreferences(context)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)

    Log.i("ID DO USUÁRIO: ", userIdFlow.value.toString())

    androidx.compose.material3.TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFEBEBEB)
        ),
        navigationIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .height(100.dp)
                    .width(170.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    painter = painterResource(R.drawable.logo_touccan),
                    tint= Color.Black,
                    contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                )
            }

        },
        title = {
        },
        actions = {
            Row(horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.configuracoes),
                        contentDescription = "Configurações: Ícone de engrenagem",
                        tint= Color.Black
                    )
                }
                IconButton(onClick = {
                    navController.navigate("cofrinho")
                }) {
                    Icon(
                        painter = painterResource(R.drawable.carteira),
                        contentDescription = "Carteira: Ícone de carteira",
                        tint= Color.Black
                    )
                }
                IconButton(onClick = {
                    val route = "perfilUsuario/${userIdFlow.value}"
                    Log.i("Rota: ", route)
                    navController.navigate(route)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.person),
                        contentDescription = "Perfil: Ícone de pessoa",
                        tint= Color.Black
                    )
                }
            }
        }

    )
}

@Composable
fun BottomAppBar(navController: NavHostController, context: Context) {
    androidx.compose.material3.BottomAppBar(
        containerColor = Color(0xFFEBEBEB)
    ) {

        var userId = remember { mutableStateOf("") }

        val userPreferences = UserPreferences(context)
        val userIdFlow = userPreferences.userId.collectAsState(initial = null)

        Log.i("ID DO USUÁRIO: ", userIdFlow.value.toString())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = {
                    val userId = Json.encodeToString(userIdFlow.value?.let { UserId(id = it) })
                    navController.navigate("home/${userId}")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home: Ícone de casa",
                )
            }
            IconButton(
                onClick = {
                    navController.navigate("historico/${userIdFlow.value}")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.historico),
                    contentDescription = "Histórico: Ícone de relógio",
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(R.drawable.notificacao),
                    contentDescription = "Notificação: Ícone de sino",
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("chatList")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = "Mensagens: Ícone de carta",
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.pesquisa),
                    contentDescription = "Pesquisa: Ícone de lupa",
                )
            }
        }

    }
}