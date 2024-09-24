package br.senai.sp.jandira.touccanuser.screens

import android.R.color
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
//    navController: NavHostController
    val laranja = 0xffF07B07

    val cinza = 0xffC6C5C5

    var pertoDeVoceState = remember{
        mutableStateOf(false)
    }

    var urgenteState = remember{
        mutableStateOf(true)
    }

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
                        )
                    }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.carteira),
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.person),
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
        Column (Modifier.padding(vertical = 100.dp)){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround

            ){
                Button(
                    onClick = {
                        urgenteState.value = false
                        pertoDeVoceState.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {

                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Perto de você",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = if(pertoDeVoceState.value) {FontStyle.Italic}
                            else {FontStyle.Normal},
                            fontWeight = FontWeight.SemiBold,
                            color = if(pertoDeVoceState.value) {Color.Black }
                            else {Color(cinza)}
                        )
                        if(pertoDeVoceState.value){
                            Box(
                                modifier = Modifier
                                    .background(Color(laranja))
                                    .height(1.dp)
                                    .width(100.dp)

                            )
                        }
                    }

                }
                Button(onClick = {
                    urgenteState.value = true
                    pertoDeVoceState.value = false
                },
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Urgente",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = if(urgenteState.value) {FontStyle.Italic}
                            else {FontStyle.Normal},
                            fontWeight = FontWeight.SemiBold,
                            color = if(urgenteState.value) {Color.Black }
                                    else {Color(cinza)}
                        )
                        if(urgenteState.value){
                            Box(
                                modifier = Modifier
                                    .background(Color(laranja))
                                    .height(1.dp)
                                    .width(100.dp)

                            )
                        }
                    }
                }
            }
            LazyColumn (contentPadding = PaddingValues(0.dp)){
                items(5){
                    AnuncioCard()
                }

            }
        }

    }

}

@Composable
fun AnuncioCard(modifier: Modifier = Modifier) {

    val grayColor = 0xff6D6D6D
    val greenColor = 0xff106B16
    val cinzaEscuro = 0xff888888

    Row (modifier = Modifier.padding(16.dp)){
        Card (
            modifier = Modifier.size(35.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(50.dp)
        ){  }
        Column (
            modifier = modifier.padding(vertical = 4.dp, horizontal = 6.dp)
        ){

            Text(
                text = "Empresa 1",
                modifier = Modifier.padding(bottom = 10.dp, start = 4.dp),
                fontFamily = Inter,
                color = Color(cinzaEscuro),
                fontWeight = FontWeight.Normal
            )
            Card {
                Row (modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color.White)){
                    ElevatedCard (
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xffF07B07),
                        ),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 70.dp
                        )
                    ){}
                    Column (
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        Text(
                            text = "Assistente Admnistrativo",
                            fontFamily = Inter,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(grayColor)
                        )
                        Text(
                            text = "Trabalho focado em organizar e atender clientes com intuito de disponibilidade hoje!",
                            fontFamily = Inter,
                            fontSize = 15.sp,
                            lineHeight = 15.sp,
                            color = Color(cinzaEscuro)
                        )
                        Text(
                            text = "Jandira - SP",
                            fontFamily = Inter,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(grayColor)
                        )
                        Row {
                            Text(
                                text = "Dificuldade: ",
                                fontFamily = Inter,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(grayColor)
                            )
                            Text(
                                text = "Baixa",
                                fontFamily = Inter,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(greenColor)
                            )
                        }

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ){ Button(
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffF07B07)),
                            onClick = {}
                        ) {
                            Text(
                                text ="Candidatar-se",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 12.sp,
                                color = Color.White
                            )
                        } }
                    }
                }
            }

        }
    }

}


//@Preview (showSystemUi = true, showBackground = true)
//@Composable
//private fun HomePreview() {
//    Surface (modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
//        Home()
//    }
//
//}