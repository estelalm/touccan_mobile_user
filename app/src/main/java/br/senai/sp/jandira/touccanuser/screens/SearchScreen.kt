package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.Categoria
import br.senai.sp.jandira.touccanuser.model.ResultBicos
import br.senai.sp.jandira.touccanuser.service.CategoriasRes
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, mainActivity: MainActivity) {
    Scaffold(
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, mainActivity)
        },
        bottomBar = {
            br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, mainActivity)
        }
    ) { innerpadding ->
        val userPreferences = UserPreferences(mainActivity)
        val userIdFlow = userPreferences.userId.collectAsState(initial = 0)


        var searchState = remember {
            mutableStateOf("")
        }
        var activeState = remember {
            mutableStateOf(false)
        }

        var bicosList = remember {
            mutableStateOf(listOf<Bico>())
        }

        var errorBico = remember{
            mutableStateOf(false)
        }

        val callBicoList = RetrofitFactory()
            .getBicoService()
            .getAllBicos()

        callBicoList.enqueue(object: Callback<ResultBicos>{
            override fun onResponse(call: Call<ResultBicos>, res: Response<ResultBicos>) {
                val bicos = res.body()?.bicos
                if(bicos != null){
                    bicosList.value = bicos
                }else{
                    Log.i("Error: ", "A lista de bicos retornou nula")
                }
            }

            override fun onFailure(call: Call<ResultBicos>, t: Throwable) {
                Log.i("Falhou:", t.toString())
                errorBico.value = true
            }
        })

        var categoriasState = remember { mutableStateOf(listOf<Categoria>()) }

        val callCatList = RetrofitFactory()
            .getBicoService()
            .getAllCategorias()

        callCatList.enqueue(object : Callback<CategoriasRes> {
            override fun onResponse(call: Call<CategoriasRes>, res: Response<CategoriasRes>) {
                val categorias = res.body()?.categorias
                if (categorias != null) {
                    categoriasState.value = categorias
                } else {
                    Log.i("Error: ", "A lista de categorias retornou nula")
                }
            }

            override fun onFailure(p0: Call<CategoriasRes>, t: Throwable) {
                Log.i("Falhou:", t.toString())
            }
        })

        Column(
            modifier = Modifier.padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchBar(query = searchState.value,
                onQueryChange = {
                    searchState.value = it
                }, onSearch = {}, active = false, onActiveChange = {},
                colors = SearchBarDefaults.colors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp),
                trailingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = "", tint = Color.Gray)
                },
                placeholder = {
                    Text(
                        text = "O que você procura?",
                        color = Color.Gray,
                        fontFamily = Inter,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier
                    .padding(12.dp)
                    .height(70.dp)

            ) {}

            Spacer(modifier = Modifier.height(24.dp))


            if (searchState.value.isEmpty()) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(
//                        "Categorias",
//                        color = Color.Black,
//                        fontFamily = Inter,
//                        fontWeight = FontWeight.Bold,
//                        fontStyle = FontStyle.Italic,
//                        fontSize = 20.sp
//                    )
//                    Box(
//                        modifier = Modifier
//                            .background(Color(MainOrange.value))
//                            .height(1.dp)
//                            .width(200.dp)
//
//                    )
//                }
//
//                LazyColumn(modifier = Modifier.padding(18.dp)) {
//                    items(categoriasState.value) { categoria ->
//                        CardCategoria(categoria)
//                    }
//                }
            } else {

                LazyColumn() {
                    items(bicosList.value) { bico ->

                        if(bico.titulo.lowercase().contains(searchState.value.lowercase())){
                            userIdFlow.value?.let { AnuncioCard(bico, navController, it, mainActivity, false ) }
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun CardCategoria(categoria: Categoria) {

    Card (modifier = Modifier.fillMaxWidth().height(60.dp).padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)){
        Row (modifier = Modifier.padding(16.dp)){
            Text(
                categoria.categoria,
                color = Color.Black,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )
        }
    }
}