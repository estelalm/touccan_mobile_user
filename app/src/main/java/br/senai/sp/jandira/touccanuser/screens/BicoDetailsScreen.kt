package br.senai.sp.jandira.touccanuser.screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.Candidato
import br.senai.sp.jandira.touccanuser.model.ResultBico
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BicoDetails(
    navController: NavHostController,
    idBico: String,
    mainActivity: MainActivity,
    candidatado: Boolean, ) {

    val userPreferences = UserPreferences(mainActivity)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)
    val user = userIdFlow.value

    val mainOrange = 0xffF07B07

    var bico = remember{ mutableStateOf( Bico())}


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
            Log.i("Response: ", res.toString())
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
        
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (modifier = Modifier.fillMaxWidth()){
                IconButton(onClick = {
                    navController.popBackStack()
                }) {Icon(imageVector = Icons.Filled.ArrowBack, "Seta voltar", modifier = Modifier.height(24.dp)) }


            }
            if(isLoadingState.value){
                CircularProgressIndicator(color = Color(mainOrange))
            }else{
                Spacer(modifier = Modifier.height(100.dp))
                Card (
                    modifier = Modifier.padding(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0x75D9D9D9)
                    )
                ){
                    Column (
                        modifier = Modifier.padding(12.dp)
                    ){


                        Column (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){


                                Icon(painter = painterResource(R.drawable.person), contentDescription = "", tint = Color(0xff7E7E7E))
                                Text(bico.value.cliente[0].nome_fantasia,
                                    color = Color(0xff504D4D),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier
                                        .padding(start = 6.dp)
                                        .fillMaxWidth())
                            }
                            Row {
                                Text("Dificuldade: ",
                                    color = Color(0xff7E7E7E),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.SemiBold)
                                Text(text = bico.value.dificuldade[0].dificuldade,
                                    color = Color(0xff106B16),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.SemiBold)
                            }
                        }
                        Column (
                            modifier = Modifier.padding(start = 12.dp)
                        ){
                            Text(
                                bico.value.titulo,
                                color = Color(0xff464646),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 12.dp))
                            Text(
                                bico.value.descricao,
                                color = Color(0xff736C6C),
                                fontFamily = Inter,
                                fontWeight = FontWeight.Light)


                            Text("Av. Diniz, 57  Jandira-SP",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                textAlign = TextAlign.End,
                                color = Color(0xff7E7E7E),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold)


                            Column (){

                                val sb = StringBuilder("")
                                Text(
                                    "Data: ${
                                        bico.value.data_inicio.split("T")[0].split("-").reversed()
                                            .joinToString( "/")
                                    }",
                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black
                                )
                                Text(
                                    "Início: ${
                                        bico.value.horario_inicio.split("T")[1].split(".")[0].split(
                                            ":").slice(0..1).joinToString(":", postfix = "h")
                                    }",
                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black
                                )
                                Text(
                                    "Término: ${
                                        bico.value.horario_limite.split("T")[1].split(".")[0].split(
                                            ":"
                                        ).slice(0..1).joinToString(":", postfix = "h")
                                    }",
                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black
                                )
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
                LaunchedEffect(Unit) {
                    while (true) { // Atualiza a cada segundo
                        val agora = LocalDateTime.now()
                        finishState.value = when {
                            dataHoraFinal.isBefore(agora) -> false
                            dataHoraFinal.isAfter(agora) -> true
                            else -> true
                        }
                        delay(2000) // Espera 1 segundo
                    }
                }
                val candidatou = remember{
                    mutableStateOf(false)
                }
                if(candidatado || candidatou.value){
                    Text("Aguardando confirmação", fontFamily = Inter, fontWeight = FontWeight.Black)
//                    Button(
//                        onClick = {
//
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(mainOrange)
//                        ),
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier.padding(top = 24.dp)
//                    ) {
//                        Text("Iniciar um bate-papo", fontFamily = Inter, fontWeight = FontWeight.Black)
//                    }
//                    Button(
//                        onClick = {
//
//                        },
//                        enabled = finishState.value,
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(mainOrange)
//                        ),
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier.padding(top = 24.dp)
//                    ) {
//                        Text("Finalizar trabalho", fontFamily = Inter, fontWeight = FontWeight.Black)
//                    }
                }else{
                    var toastMessageState = remember{
                        mutableStateOf("")
                    }

                    Button(

                        onClick = {


                            val candidato = user?.let {
                                Candidato(
                                    id_bico = bico.value.id,
                                    id_user = it
                                )
                            }

                            val postCandidato = candidato?.let {
                                RetrofitFactory()
                                    .getBicoService()
                                    .postCandidato(it)
                            }

                            if (postCandidato != null) {
                                postCandidato.enqueue(object: Callback<Candidato>{
                                    override fun onResponse(call: Call<Candidato>, res: Response<Candidato>){
                                        toastMessageState.value = "Candidatou-se com sucesso"
                                        candidatou.value = true
                                        Toast.makeText(mainActivity, toastMessageState.value, Toast.LENGTH_SHORT).show()

                                    }

                                    override fun onFailure(call: Call<Candidato>, t: Throwable){
                                        Log.i("Falhou:", t.toString())
                                        toastMessageState.value = "Falha em candidatar-se"
                                        Toast.makeText(mainActivity, toastMessageState.value, Toast.LENGTH_SHORT).show()
                                    }
                                })
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(mainOrange)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.padding(top = 24.dp)
                    ) {
                        Text("Candidatar-se", fontFamily = Inter, fontWeight = FontWeight.Black)
                    }
                }

            }






        }


    }


}



//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun BicoDetailsPreview() {
//    BicoDetails(navController, idBico, this@MainActivity)
//}

