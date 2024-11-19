package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.BicoHistorico
import br.senai.sp.jandira.touccanuser.model.HistoryResult
import br.senai.sp.jandira.touccanuser.model.ResultBicos
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun History(navController: NavHostController, idUser: Int, mainActivity: MainActivity) {

    var historyList = remember {
        mutableStateOf(listOf<BicoHistorico>())
    }
    var isLoadingState = remember {
        mutableStateOf(true)
    }
    var errorState = remember {
        mutableStateOf(false)
    }

    val callBico = RetrofitFactory()
        .getBicoService()
        .getBicoByUsuario(idUser.toInt())


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

    Scaffold(
        containerColor = Color(0xFFEBEBEB),
        topBar = {
          br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, mainActivity)
        },
        bottomBar = {
           br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, mainActivity)
        }
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Histórico",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
                Box(
                    modifier = Modifier
                        .background(Color(MainOrange.value))
                        .height(1.dp)
                        .width(200.dp)

                )
            }

            LazyColumn (modifier = Modifier.padding(top = 24.dp).fillMaxSize()) {

                if(historyList.value.isEmpty()){
                    item {
                        Text("Seu histórico está vazio",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth())
                    }
                }else{
                    items(historyList.value){ bico ->
                        HistoryCard(bico)

                    }
                }

                }

            }

        }

    }


@Composable
fun HistoryCard(bico: BicoHistorico) {


    ElevatedCard (modifier = Modifier.clickable { }.padding(horizontal = 18.dp, vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        )){
        Row (modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .background(Color.White)){
            Card (
                modifier = Modifier
                    .fillMaxHeight()
                    .width(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MainOrange,
                ),
                shape = RectangleShape
            ){}
            Row (verticalAlignment = Alignment.CenterVertically){

                Column (
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ){
                    Text( "${bico.nome_cliente} - ${bico.bico}",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold)
                    Text(bico.data_inicio.split("T")[0].split("-").reversed().joinToString("/", "Data: "))
                }



                val dataInicio = LocalDate.parse(bico.data_inicio.split("T")[0].split("-").joinToString("-"))
                val horarioInicio = LocalTime.parse(bico.horario_inicio.split("T")[1].split(".")[0].split(
                    ":"
                ).slice(0..1).joinToString(":"))

                val dataHoraInicio = LocalDateTime.of(dataInicio, horarioInicio)

                var mensagem = remember{ mutableStateOf("") }
                LaunchedEffect(Unit) {
                    while (true) { // Atualiza a cada segundo
                        val agora = LocalDateTime.now()
                        mensagem.value = when {
                            dataHoraInicio.isBefore(agora) -> "Em andamento"
                            dataHoraInicio.isAfter(agora) -> "Pendente"
                            else -> "Em andamento"
                        }
                        delay(2000) // Espera 1 segundo
                    }
                }


                if(bico.finalizado == 1){
                    Button(onClick = {}, modifier = Modifier.height(28.dp).width(70.dp).padding(0.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MainOrange)
                    ) {
                        Text("Avalie",
                            fontFamily = Inter,
                            fontSize = 8.sp,
                            modifier = Modifier.fillMaxWidth())
                    }
                    }else{
                    Text(mensagem.value,
                        fontFamily = Inter,
                        fontSize = 8.sp,
                        color = if(mensagem.value == "Em andamento"){Color(0xFFFFCC01)}else{Color(0xFF8F0B0B)},
                        modifier = Modifier.fillMaxWidth())
                    }


                }

            }

        }
    }



//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun NotPreview() {
//
//    History(navController, idUser, this@MainActivity)
//
//}