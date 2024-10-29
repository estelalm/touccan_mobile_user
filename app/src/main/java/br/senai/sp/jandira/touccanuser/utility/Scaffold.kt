package br.senai.sp.jandira.touccanuser.utility

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavHostController) {
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
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.carteira),
                        contentDescription = "Carteira: Ícone de carteira",
                    )
                }
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(R.drawable.person),
                        contentDescription = "Perfil: Ícone de pessoa",
                    )
                }
            }
        }

    )
}

@Composable
fun BottomAppBar(navController: NavHostController) {
    androidx.compose.material3.BottomAppBar(
        containerColor = Color(0xFFEBEBEB)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {


            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Home: Ícone de casa",
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.historico),
                    contentDescription = "Home: Ícone de casa",
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(R.drawable.notificacao),
                    contentDescription = "Home: Ícone de casa",
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = "Home: Ícone de casa",
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.pesquisa),
                    contentDescription = "Home: Ícone de casa",
                )
            }
        }

    }
}