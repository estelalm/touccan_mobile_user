package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEBEBEB)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .height(100.dp)
                            .width(170.dp)) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.logo_touccan),
                            tint = Color.Black,
                            contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                        )
                    }

                },
                title = {
                },
                actions = {
                    Row (horizontalArrangement = Arrangement.End){
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.configuracoes),
                                contentDescription = "Configurações: Ícone de engrenagem",
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.carteira),
                                tint = Color.Black,
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.person),
                                tint = Color.Black,
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        } }
                }

            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xFFEBEBEB)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {


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
    ) { innerpadding ->

        Column (modifier = Modifier.padding(innerpadding)){
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((50.dp)),
                colors = SearchBarDefaults.colors(containerColor = Color.Red),
                    query = "",
                    onQueryChange = {},
                    active = true,
                    onActiveChange = {}, 
                    onSearch = {}
            ) {}
            Column {
                Text("Categorias")
                Box(
                    modifier = Modifier
                        .background(Color(MainOrange.value))
                        .height(1.dp)
                        .width(100.dp)

                )
            }

            LazyColumn () {
                
            }

        }
    }
}

@Composable
fun CardCategoria() {

    Card {
        Row {
            Text("Atendente")
            Text("120 posts")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPrev() {
    SearchScreen()
}

