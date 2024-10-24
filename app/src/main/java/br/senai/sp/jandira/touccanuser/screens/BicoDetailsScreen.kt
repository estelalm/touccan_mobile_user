package br.senai.sp.jandira.touccanuser.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.ResultBico
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BicoDetails(navController: NavHostController, idBico: String, mainActivity: MainActivity) {

    Log.i("ID DO BICO", idBico)
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
            Log.i("ResponseBody:", res.body()!!.bico.toString())
            bico.value = res.body()!!.bico
            Log.i("kkkkkkkk" , bico.toString())
            Log.i("AAAAAAAA" , bico.value.cliente[0].toString())


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
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEBEBEB)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .height(100.dp)
                            .width(170.dp)) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.logo_touccan),
                            tint = Color.Black,
                            contentDescription = "Desenho de um, com o texto Touccan ao lado, a logo do aplicativo",
                        )
                    }


                },
                title = {
                },
                actions = {
                    Row (horizontalArrangement = Arrangement.End){
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.configuracoes),
                                contentDescription = "Configurações: Ícone de engrenagem",
                                tint = Color.Black
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.carteira),
                                tint = Color.Black,
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.person),
                                tint = Color.Black,
                                contentDescription = "Configurações: Ícone de engrenagem",
                            )
                        } }
                }


            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xFFEBEBEB)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {




                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.home),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.historico),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            painter = painterResource(R.drawable.notificacao),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }


                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.chat),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }


                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.pesquisa),
                            contentDescription = "Home: Ícone de casa",
                        )
                    }
                }


            }
        }
    ) { innerpadding ->

        Log.i("BBBBBB" , bico.value.toString())
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


                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically){


                                Icon(painter = painterResource(R.drawable.person), contentDescription = "", tint = Color(0xff7E7E7E))
                                Text(bico.value.cliente[0].nome_fantasia,
                                    color = Color(0xff504D4D),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(start = 6.dp).width(100.dp))
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
                                bico.value.descricao,
                                color = Color(0xff736C6C),
                                fontFamily = Inter,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(vertical = 12.dp))
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
                                Text("Início: ${bico.value.horario_inicio.split("T")[1].split(".")[0]}",
                                    color = Color(0xff464646),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Black)
                                Text("Término: ${bico.value.horario_limite.split("T")[1].split(".")[0]  }",
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


                Button(
                    onClick = {

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


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun BicoDetailsPreview() {
//    BicoDetails(navController, idBico, this@MainActivity)
//}

