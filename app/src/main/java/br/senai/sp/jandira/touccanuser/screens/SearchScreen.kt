package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
          //  br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController)
        },
        bottomBar = {
           // br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController)
        }
    ) { innerpadding ->

        var searchState = remember {
            mutableStateOf("")
        }
        var activeState = remember {
            mutableStateOf(false)
        }

        Column (modifier = Modifier.padding(innerpadding)){
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

