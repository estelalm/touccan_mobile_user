package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreNos(navController: NavHostController, context: Context) {

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {
        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, context)
            },
            bottomBar = {
                br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, context)
            }
        ) { innerpadding ->


            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) { Image(
                    painterResource(R.drawable.seta_voltar), "",
                    modifier = Modifier.height(50.dp).padding(bottom = 20.dp))

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            "Sobre nós",
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
                    }}



                Card (
                    modifier = Modifier.height(540.dp).width(300.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xA6C7C2C2))
                ){
                    Column (modifier = Modifier.fillMaxSize().padding(24.dp)){
                        Text("Quem somos", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Touccan é uma forma inovadora de conectar pessoas que precisam de empregos temporários de alta remuneração a alguém que precisa de serviços de curto prazo e eficientes. Nossa plataforma permite que as pessoas realizem suas tarefas e contrate-as, com foco naqueles que precisam apenas por um curto período. Nosso objetivo de negócio é simplificar a procura de empregos temporários e contratação de temporários.",
                            fontFamily = Inter, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Nossa missão", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Nossa missão é simplificar o processo de emprego e contratação, tornando-o ágil e acessível. Acreditamos que todos devem ter a oportunidade de encontrar trabalho que se ajuste às suas necessidades e que os empregadores merecem acesso rápido a profissionais qualificados para serviços temporários.",
                            fontFamily = Inter, fontSize = 15.sp)
                    }

                }

            }

        }




    }
}

