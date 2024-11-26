package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Seguranca() {

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {
        Scaffold(
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


            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Image(
                        painterResource(R.drawable.seta_voltar), "",
                        modifier = Modifier.height(40.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Segurança",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(MainOrange.value))
                            .height(1.dp)
                            .width(170.dp)
                    )
                    Text(
                        "Altere a senha a qualquer momento",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                var atualState = remember {
                    mutableStateOf("")
                }
                var novaSenhaState = remember {
                    mutableStateOf("")
                }
                var confirmarState = remember {
                    mutableStateOf("")
                }


                Card(
                    modifier = Modifier.height(360.dp).width(300.dp).padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xA6C7C2C2))
                ) {
                    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
                        Text(
                            "Senha atual",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        TextField(value = atualState.value, onValueChange = {atualState.value = it},
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor  = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                cursorColor = MainOrange,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            "Nova senha",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        TextField(value = novaSenhaState.value, onValueChange = {novaSenhaState.value = it},
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor  = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                cursorColor = MainOrange,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            "Confirmar senha",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        TextField(value = confirmarState.value, onValueChange = {confirmarState.value = it},
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor  = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                cursorColor = MainOrange,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )

                    }

                }

                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainOrange
                    )
                ) {
                    Text(
                        text = "Atualizar senha",
                        color = Color.White,
                        fontFamily = Inter,
                        fontSize = 18.sp,
                    )
                }
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Esqueceu sua senha?",
                        fontSize = 14.sp,
                        color = Color(0xff515151),
                        fontFamily = Inter,
                        lineHeight = 10.sp
                    )
                }

            }


        }
    }
}

@Preview()
@Composable
private fun SegurancaPrev() {
    Seguranca()
}