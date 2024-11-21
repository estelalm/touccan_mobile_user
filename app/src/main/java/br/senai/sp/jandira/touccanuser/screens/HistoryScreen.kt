package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.model.AceitosResult
import br.senai.sp.jandira.touccanuser.model.BicoHistorico
import br.senai.sp.jandira.touccanuser.model.Candidatos
import br.senai.sp.jandira.touccanuser.model.Contratado
import br.senai.sp.jandira.touccanuser.model.HistoryResult
import br.senai.sp.jandira.touccanuser.model.ResultCandidatos
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
                        HistoryCard(bico, idUser, navController)

                    }
                }

                }

            }

        }

    }


@Composable
fun HistoryCard(bico: BicoHistorico, idUser: Int, navController: NavHostController) {

    var aceitoState = remember{
        mutableStateOf(Contratado())
    }

    val callStatusAceito = RetrofitFactory()
        .getBicoService()
        .getContratadosByBico(bico.id_bico)


    callStatusAceito.enqueue(object: Callback<AceitosResult>{
        override fun onResponse(call: Call<AceitosResult>, res: Response<AceitosResult>) {
            Log.i("STATUS DE ACEITAÇÃO PARE DE SE ODIAR",res.body().toString())
            if(res.code() == 200){
                aceitoState.value = res.body()!!.candidatos
            }
        }

        override fun onFailure(call: Call<AceitosResult>, t: Throwable) {
            Log.i("Falhou:", t.toString())
            t.printStackTrace()
        }
    })


    var candidateList = remember {
        mutableStateOf(listOf<Candidatos>())
    }

    val callCandidateList = RetrofitFactory()
        .getBicoService()
        .getCandidatosByBico(bico.id_bico)

    callCandidateList.enqueue(object: Callback<ResultCandidatos>{
        override fun onResponse(call: Call<ResultCandidatos>, res: Response<ResultCandidatos>) {
            if(res.code() == 200){
                candidateList.value = res.body()!!.candidatos
            }
        }

        override fun onFailure(call: Call<ResultCandidatos>, t: Throwable) {
            Log.i("Falhou:", t.toString())
            t.printStackTrace()
        }
    })

    var candidatado = remember{
        mutableStateOf(false)
    }

    if(candidateList.value.isNotEmpty()){
        for (item in candidateList.value){
            if (item.id_candidato == idUser){
                candidatado.value = true
            }
        }
    }else{

    }

    //checar a data de término para finalizar o trabalho
    val dataInicio = LocalDate.parse(bico.data_inicio.split("T")[0].split("-").joinToString("-"))
    val horarioInicio = LocalTime.parse(bico.horario_inicio.split("T")[1].split(".")[0].split(
        ":"
    ).slice(0..1).joinToString(":"))

    val dataFinal = LocalDate.parse(bico.data_limite.split("T")[0].split("-").joinToString("-"))
    val horarioFinal = LocalTime.parse(bico.horario_limite.split("T")[1].split(".")[0].split(
        ":"
    ).slice(0..1).joinToString(":"))

    val dataHoraInicio = LocalDateTime.of(dataInicio, horarioInicio)
    val dataHoraFinal = LocalDateTime.of(dataFinal, horarioFinal)

    var mensagemCandidato = remember{ mutableStateOf("") }

        mensagemCandidato.value = when{
            aceitoState.value.escolhido == 1 && aceitoState.value.id_canditado == idUser -> "Aceito"
            aceitoState.value.escolhido == 1 && aceitoState.value.id_canditado != idUser -> "Você não foi contratado para esse trabalho!"
            aceitoState.value.escolhido != 1 -> "Aguardando contratação!"
            else -> {"Aguardando contratação"}
        }

    var mensagemAceito = remember{ mutableStateOf("") }
    LaunchedEffect(Unit) {
        while (true) { // Atualiza a cada segundo
            val agora = LocalDateTime.now()
            mensagemAceito.value = when {
                dataHoraInicio.isBefore(agora) -> "Em andamento"
                dataHoraInicio.isAfter(agora) -> "Pendente"
                dataHoraFinal.isBefore(agora) -> "Aguardando finalização"
                else -> "Em andamento"
            }
            delay(2000) // Espera 1 segundo
        }
    }


    ElevatedCard (
        modifier = Modifier.clickable {
            if(aceitoState.value.id_canditado == idUser){
                navController.navigate("meuBico/${bico.id_bico}/${candidatado.value}")
            }else{
                if(aceitoState.value.escolhido == 0){
                    navController.navigate("bico/${bico.id_bico}/${candidatado.value}")
                }else{

                }
            }

        }.padding(horizontal = 18.dp, vertical = 8.dp),
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
            Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){

                Column (
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text( bico.nome_cliente,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.width(100.dp),
                            maxLines = 1,
                            color = Color.Black
                        )
                        Text( bico.bico,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(bico.data_inicio.split("T")[0].split("-").reversed().subList(0, 2).joinToString("/"), color = Color.Black)
                        Spacer(modifier = Modifier.width(20.dp))
                        if(aceitoState.value.escolhido == 0){
                            Text(mensagemCandidato.value,
                                fontFamily = Inter,
                                fontSize = 10.sp,
                                color = Color.Black,
                                modifier = Modifier.fillMaxWidth())
                        }else{
                            if(aceitoState.value.id_canditado == idUser){
                                if(bico.finalizado == 0){

                                    Text(mensagemAceito.value,
                                        fontFamily = Inter,
                                        fontSize = 10.sp,
                                        color = if(mensagemAceito.value == "Em andamento"){Color(0xFFFFCC01)}else{Color(0xFF8F0B0B)},
                                        modifier = Modifier.fillMaxWidth())
                                }else{
                                    Button(onClick = {
                                        navController.navigate("avaliacao/${idUser}")

                                    }, modifier = Modifier.height(28.dp).width(70.dp).padding(0.dp),
                                        shape = RoundedCornerShape(16.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = MainOrange)
                                    ) {
                                        Text("Avalie",
                                            fontFamily = Inter,
                                            fontSize = 8.sp,
                                            modifier = Modifier.fillMaxWidth())
                                    }
                                }
                            }else{
                                Text(mensagemCandidato.value,
                                    fontFamily = Inter,
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                    modifier = Modifier.fillMaxWidth())
                            }
                        }

                    }
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