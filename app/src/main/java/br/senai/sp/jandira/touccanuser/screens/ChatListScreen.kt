package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatList(navController: NavHostController, mainActivity: MainActivity) {

    var chatListState = remember {
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {
        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFEBEBEB)
                    ),
                    title = { Text("") },
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                        ) {
                            IconButton(onClick = {}) {
                                Icon(
                                    tint = Color.Black,
                                    painter = painterResource(R.drawable.configuracoes),
                                    contentDescription = "Configurações: Ícone de engrenagem",
                                )
                            }
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    tint = Color.Black,
                                    painter = painterResource(R.drawable.person),
                                    contentDescription = "Perfil: Ícone de pessoa",
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(0xFFEBEBEB),
                    content = {
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
                )
            }
        ) { innerpadding ->

        var searchState = remember {
            mutableStateOf("")
        }
            var activeState = remember {
                mutableStateOf(false)
            }

            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(70.dp)
                            .padding(bottom = 24.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 8.dp) // Ajuste fino para centralizar o conteúdo
                        ) {
                            SearchBar(
                                modifier = Modifier
                                    .offset(y= -18.dp)
                                    .fillMaxHeight() // Garante que ocupe toda a altura disponível
                                    .fillMaxWidth(),
                                query = searchState.value,
                                onQueryChange = { searchState.value = it },
                                active = activeState.value,
                                onActiveChange = { activeState.value = it },
                                onSearch = { /* Ação ao realizar a pesquisa */ },
                                placeholder = {
                                    Text(
                                        text = "Pesquisar conversas",
                                        color = Color.Gray,
                                    )
                                },
                                colors = SearchBarDefaults.colors(
                                    containerColor = Color.Transparent
                                )
                            ){}
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(160.dp)
                    )
                }


                if (!chatListState.value) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Image(
                            painter = painterResource(R.drawable.logo_sombra),
                            contentDescription = "Tucano preto, logo da Touccan sem o texto.",
                            modifier = Modifier.height(300.dp)
                        )
                        Text(
                            "Você não possui mensagens...",
                            modifier = Modifier.offset(y = -50.dp),
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp,
                            color = Color(0xff9F9C9C)

                        )
                    }

                } else {
                    LazyColumn (
                        modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                            .padding(vertical = 20.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)

                    ){
                        items(3){
                            ChatCard()
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun ChatCard() {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier =Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .height(68.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.size(30.dp).clip(RoundedCornerShape(50.dp))){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.person),
                    contentDescription = "Foto de perfil do usuário tal"
                )
            }
            Column (modifier = Modifier.padding(start = 12.dp)){
                Text(
                    text = "Mercadinho Bom Lugar",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff726F6F)
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        modifier = Modifier.width(200.dp),
                        text = "Mensagem Blablabla Bla blaaaaaaaaaaaaaaaaaaaa.",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        )
                    Text(text = "10:36")
                }
            }
        }


    }
}

@Preview (showSystemUi = true, showBackground = true)
@Composable
private fun ChatListPrev() {
    ChatList(navController, this@MainActivity)
}