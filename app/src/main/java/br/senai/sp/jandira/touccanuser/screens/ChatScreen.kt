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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat() {

    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {

        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFEBEBEB)
                    ),
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = {
                        }) {
                            Icon(
                                tint = Color.Black,
                                painter = painterResource(R.drawable.seta_voltar),
                                contentDescription = "Seta para a esquerda: Voltar",
                            )
                        }
                    },
                    actions = {
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    tint = Color.Black,
                                    painter = painterResource(R.drawable.person),
                                    contentDescription = "Perfil: Ícone de pessoa",
                                )
                            }
                    }
                )
            }
        ){innerpadding ->

            Column(
                modifier = Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text= "Mercado Bom Lugar")
                Box(
                    modifier = Modifier
                        .background(Color(MainOrange.value))
                        .height(1.dp)
                        .width(200.dp)

                )

                LazyColumn {
                    
                }
                TextField(
                    value = "",
                    onValueChange = {})

            }


        }

    }

    
}

@Composable
fun Message() {
    
}

@Preview
@Composable
private fun ChatPrev() {
    Chat()
}