package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.Finalizar
import br.senai.sp.jandira.touccanuser.model.FinalizarCliente
import br.senai.sp.jandira.touccanuser.model.ResultBico
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeuBicoDetails(
    navController: NavHostController,
    idBico: String,
    mainActivity: MainActivity,
    candidatado: Boolean) {

    val userPreferences = UserPreferences(mainActivity)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)
    val userId = userIdFlow.value

    val mainOrange = 0xffF07B07

    var bico = remember{ mutableStateOf( Bico()) }


    var isLoadingState = remember{
        mutableStateOf(true)
    }
    var errorState = remember {
        mutableStateOf(false)
    }

    val callBico = RetrofitFactory()
        .getBicoService()
        .getBicoById(idBico.toInt())



    callBico.enqueue(object: Callback<ResultBico> {
        override fun onResponse(call: Call<ResultBico>, res: Response<ResultBico>) {
            Log.i("Response Meu Bico: ", res.toString())
            bico.value = res.body()!!.bico

            isLoadingState.value = false
        }

        override fun onFailure(call: Call<ResultBico>, t: Throwable) {
            Log.i("Falhou:", t.toString())
            errorState.value = true
        }
    })



    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, mainActivity)
        },
        bottomBar = {
            br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, mainActivity)
        }
    ) { innerpadding ->

        if(bico.value.id != 0){
            Column (
                modifier = Modifier.fillMaxSize().padding(innerpadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Row (modifier = Modifier.fillMaxWidth()){
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {Icon(imageVector = Icons.Filled.ArrowBack, "Seta voltar",
                        modifier = Modifier.height(24.dp)) }


                }

                Card (
                    modifier = Modifier.width(300.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0x75D9D9D9)
                    )
                ){
                    Column (
                        modifier = Modifier.padding(12.dp)
                    ){

                        Column (
                            verticalArrangement= Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically){

                                Icon(painter = painterResource(R.drawable.person), contentDescription = "", tint = Color(0xff7E7E7E))
                                Text(bico.value.cliente[0].nome_fantasia,
                                    color = Color(0xff504D4D),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(start = 6.dp))
                            }
                            Row {
                                Text("Dificuldade: ",
                                    color = Color(0xff7E7E7E),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.SemiBold)
                                Text("Baixa",
                                    color = Color(0xff106B16),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.SemiBold)
                            }
                        }
                        Column (
                            modifier = Modifier.padding(start = 12.dp)
                        ){

                            Text(bico.value.titulo,
                                color = Color(0xff464646),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 12.dp))
                            Text(bico.value.descricao,
                                color = Color(0xff736C6C),
                                fontFamily = Inter,
                                fontWeight = FontWeight.Light)

                            Text("Av. Diniz, 57  Jandira-SP",
                                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                                textAlign = TextAlign.End,
                                color = Color(0xff7E7E7E),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold)

                            Column (){
                                Text("Início:                                 ${
                                    bico.value.horario_inicio.split("T")[1].split(".")[0].split(
                                        ":").slice(0..1).joinToString(":", postfix = "h")
                                }",


                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black)
                                Text("Término:                                 ${
                                    bico.value.horario_limite.split("T")[1].split(".")[0].split(
                                        ":"
                                    ).slice(0..1).joinToString(":", postfix = "h")
                                }",
                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black)
                                Row {
                                    Text("Pagamento: ",
                                        color = Color(0xff464646),
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Black)
                                    Text("R$${bico.value.salario}",
                                        color = Color(0xff378420),
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Black)
                                }
                            }
                        }
                    }
                }


                val dataFinal = LocalDate.parse(bico.value.data_limite.split("T")[0].split("-").joinToString("-"))
                val horarioFinal = LocalTime.parse(bico.value.horario_limite.split("T")[1].split(".")[0].split(
                    ":"
                ).slice(0..1).joinToString(":"))
                val dataHoraFinal = LocalDateTime.of(dataFinal, horarioFinal)
                var finishState = remember{ mutableStateOf(false) }
                Log.i("Ainda não chegou a hora?", dataHoraFinal.isBefore(LocalDateTime.now()).toString())
                LaunchedEffect(Unit) {
                    while (true) { // Atualiza a cada segundo
                        val agora = LocalDateTime.now()
                        finishState.value = when {
                            dataHoraFinal.isBefore(agora) -> true
                            dataHoraFinal.isAfter(agora) -> false
                            else -> false
                        }
                        delay(2000) // Espera 1 segundo
                    }
                }



                if(bico.value.finalizado == 1){
                    Button(onClick = {
                        navController.navigate("avaliacao/${userId}")

                    }, modifier = Modifier.height(50.dp).width(100.dp).padding(top = 12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(mainOrange),
                            disabledContainerColor = Color.LightGray,
                            contentColor = Color.Black,
                            disabledContentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Avalie",
                            fontFamily = Inter,
                            fontSize = 8.sp,
                            modifier = Modifier.fillMaxWidth())
                    }

                }else{
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(mainOrange)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.padding(top = 24.dp)
                    ) {
                        Text("Começar um Bate-papo", fontFamily = Inter, fontWeight = FontWeight.Black)
                    }
                }

                Button(
                    onClick = {

                        val infoFinalizar = Finalizar(
                            id_bico = bico.value.id,
                            final_u = 1
                        )

                        val callFinalizar = RetrofitFactory()
                            .getBicoService()
                            .finalizarUsuario(infoFinalizar)

                        callFinalizar.enqueue(object: Callback<Finalizar> {
                            override fun onResponse(call: Call<Finalizar>, res: Response<Finalizar>) {
                                Log.i("Response finalizar usuario: ", res.toString())
                            }
                            override fun onFailure(call: Call<Finalizar>, t: Throwable) {
                                Log.i("Falhou:", t.toString())
                            }
                        })

                        val infoFinalizarCliente = FinalizarCliente(
                            id_bico = bico.value.id,
                            final_c = 1
                        )

                        val callFinalizarCliente = RetrofitFactory()
                            .getBicoService()
                            .finalizarCliente(infoFinalizarCliente)

                        callFinalizarCliente.enqueue(object: Callback<FinalizarCliente> {
                            override fun onResponse(call: Call<FinalizarCliente>, res: Response<FinalizarCliente>) {
                                Log.i("Response finalizar cliente: ", res.toString())
                            }
                            override fun onFailure(call: Call<FinalizarCliente>, t: Throwable) {
                                Log.i("Falhou:", t.toString())
                            }
                        })


                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(mainOrange),
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.Black,
                        disabledContentColor = Color.Black
                    ),
                    enabled = if(bico.value.finalizado == 1){false}else{finishState.value} ,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Trabalho finalizado", fontFamily = Inter, fontWeight = FontWeight.Black)
                }


            }
        }else{

        }


    }

}