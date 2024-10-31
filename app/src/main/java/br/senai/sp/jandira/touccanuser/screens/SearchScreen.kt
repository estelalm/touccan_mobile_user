package br.senai.sp.jandira.touccanuser.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
          //  br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController)
        },
        bottomBar = {
           // br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController)
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

