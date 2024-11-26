package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.BicoHistorico
import br.senai.sp.jandira.touccanuser.model.HistoryResult
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cofre(navController: NavHostController, context: Context) {

    val userPreferences = UserPreferences(context)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)

    var historyList = remember {
        mutableStateOf(listOf<BicoHistorico>())
    }
    var isLoadingState = remember {
        mutableStateOf(true)
    }
    var errorState = remember {
        mutableStateOf(false)
    }

    val callBico = userIdFlow.value?.let {
        RetrofitFactory()
        .getBicoService()
        .getBicoByUsuario(it.toInt())
    }


    if (callBico != null) {
        callBico.enqueue(object: Callback<HistoryResult> {
            override fun onResponse(call: Call<HistoryResult>, res: Response<HistoryResult>) {
                Log.i("Response: ", res.toString())

                val bicos = res.body()?.bicos
                Log.i("Bicos: ", bicos.toString())
                if(bicos != null){
                    historyList.value = bicos
                }else{
                    Log.i("Error: ", "A lista de bicos retornou nula")
                }

                isLoadingState.value = false
            }

            override fun onFailure(call: Call<HistoryResult>, t: Throwable) {
                Log.i("Falhou:", t.toString())
                errorState.value = true
            }
        })
    }



    Surface(modifier = Modifier.background(Color(0xffEBEBEB))) {
        Scaffold(
            containerColor = Color(0xFFEBEBEB),
            topBar = { br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, context) },
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

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 50.dp)
                ) {
                    Text(
                        "Cofrinho",
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
                            .width(160.dp)
                    )

                }

                Text("Faturamento mensal",
                    fontSize = 24.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold)
                Text("Período 1 mês",
                    fontSize = 12.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,modifier = Modifier.padding(vertical = 12.dp))
                Text("R$ 520,00",
                    fontSize = 24.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold)

                LazyColumn (
                    modifier = Modifier.padding(top = 24.dp).fillMaxHeight(0.7F)
                ) {
                    items(historyList.value){
                        HistoryCard(it)
                    }

                }


                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MainOrange
                        ),
                        modifier = Modifier.padding(6.dp),
                        onClick = {}
                    ) {
                        Text(
                            "Meu cartão",
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontSize = 18.sp
                        )
                    }
                Text("Em qual conta gostaria de receber seu dinheiro?",
                     fontFamily = Inter, textAlign = TextAlign.Center,
                    fontSize = 12.sp, modifier = Modifier.width (180.dp))



                }

            }

        }
    }


@Composable
fun HistoryCard(bico: BicoHistorico) {
    Card (
        modifier = Modifier
            .height(80.dp)
            .width(300.dp)
            .padding(bottom = 12.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            val dataInicio = LocalDate.parse(bico.data_inicio.split("T")[0].split("-").joinToString("-"))
            val horarioInicio = LocalTime.parse(bico.horario_inicio.split("T")[1].split(".")[0].split(
                ":"
            ).slice(0..1).joinToString(":"))

            Column (modifier = Modifier.padding(start= 12.dp)){
                Text("${bico.nome_cliente} · ${bico.bico}",
                    fontFamily = Inter,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
                    overflow = TextOverflow.Ellipsis)
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text("R$${bico.salario}",
                        fontFamily = Inter,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Column (modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom) {
                        Text("${dataInicio} - ${horarioInicio}",
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray)
                    }
                    }


            }
        }


    }
}
