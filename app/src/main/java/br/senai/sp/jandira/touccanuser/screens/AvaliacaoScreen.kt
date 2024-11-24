package br.senai.sp.jandira.touccanuser.screens


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.AvaliacaoUser
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import br.senai.sp.jandira.touccanuser.viewmodel.AvBicoViewModel
import br.senai.sp.jandira.touccanuser.viewmodel.FeedbackViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Avaliacao(navController: NavHostController, idBico: String, mainActivity: MainActivity) {

    val userPreferences = UserPreferences(mainActivity)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)

    val feedbackViewModel: FeedbackViewModel = viewModel()

    val stars by feedbackViewModel.stars.collectAsState()
    val review by feedbackViewModel.review.collectAsState()

    val bicoId =  idBico.toInt()

    var commentState = remember{
        mutableStateOf("")
    }

    var starsState = remember{
        mutableStateOf(0)
    }

    val id_cliente = idBico


    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {
            br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, mainActivity)
        },
        bottomBar = {
            br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, mainActivity)
        }
    ) { innerpadding ->
        val viewModel: AvBicoViewModel = viewModel()


        LaunchedEffect(bicoId) {
            viewModel.fetchBico(bicoId)
        }

        val bicoState by viewModel.bico.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val isError by viewModel.error.collectAsState()



        var bico = Bico()

//        var isLoadingState = remember{
//            mutableStateOf(true)
//        }
//        var errorState = remember {
//            mutableStateOf(false)
//        }
        var clienteIdState = remember{
            mutableStateOf(0)
        }

//        val callBico = RetrofitFactory()
//            .getBicoService()
//            .getBicoById(bicoId)
//
//
//        callBico.enqueue(object: Callback<ResultBico> {
//            override fun onResponse(call: Call<ResultBico>, res: Response<ResultBico>) {
//                Log.i("BICO DA AVALIAÇÃO: ", res.body().toString())
//
//                val body = res.body()
//                if (body != null && body.bico.id != 0) {
//                    bico = body.bico
//                    clienteIdState.value = bico.cliente[0].id
//                } else {
//                    // voltou nulo vishhh
//                }
//                Log.i("CLIENTE DO BICO: ", bico.cliente[0].toString())
//
//                isLoadingState.value = false
//            }
//
//            override fun onFailure(call: Call<ResultBico>, t: Throwable) {
//                Log.i("Falhou:", t.toString())
//                errorState.value = true
//                isLoadingState.value = false
//            }
//        })

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (modifier = Modifier.fillMaxWidth()){
                IconButton(onClick = {

                }) {Icon(imageVector = Icons.Filled.ArrowBack, "Seta voltar", modifier = Modifier.height(24.dp)) }


            }

                Spacer(modifier = Modifier.height(50.dp))
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically){

                                Log.i("CLIENTE FORAAAAA: ", "AHAHAHHAH"+ bico.toString()) //não aparece no logcat
                                Icon(painter = painterResource(R.drawable.person), contentDescription = "", tint = Color(0xff7E7E7E))
                                if (bico.id != 0) {
                                    Text(if(bico.cliente.isNotEmpty()){bico.cliente[0].nome_fantasia}else{"Nome não encontrado"}, //da erro de empty list
                                        color = Color(0xff504D4D),
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier
                                            .padding(start = 6.dp)
                                            .width(100.dp))
                                }
                            }
                            Column (horizontalAlignment = Alignment.End){
                                Text("Avalie o estabelecimento: ",
                                    color = Color.Black,
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.SemiBold)
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 2.dp), horizontalArrangement = Arrangement.End){

                                        for (i in 1..5) {
                                            Icon(
                                                imageVector = Icons.Filled.Star,
                                                contentDescription = null,
                                                tint = if (i <= stars) Color(0xFFFFBC06) else Color(0xFF504D4D),
                                                modifier = Modifier.clickable { feedbackViewModel.setStars(i) }
                                            )
                                    }

                                }

                            }
                        }
                        Column (
                            modifier = Modifier.padding(start = 12.dp)
                        ){

                            Column (){
//                                if (bico.id != 0) {
//                                    Text("Início: ${
//                                        bico.horario_inicio.split("T")[1].split(".")[0].split(
//                                            ":").slice(0..1).joinToString(":", postfix = "h")
//                                    }",
//                                        color = Color(0xff464646),
//                                        fontFamily = Inter,
//                                        fontWeight = FontWeight.Black)
//                                }
                                if (bico.id != 0) {
                                    Log.i("log.i bico blablabl", bico.toString())
                                    Text("Término: ${
                                        bico.horario_limite.split("T")[1].split(".")[0].split(
                                            ":"
                                        ).slice(0..1).joinToString(":", postfix = "h")
                                    }",
                                        color = Color(0xff464646),
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Black)
                                }
                                Row {
                                    Text("Pagamento: ",
                                        color = Color(0xff464646),
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Black)
                                    if (bico.id != 0) {
                                        Text("R$${bico.salario}",
                                            color = Color(0xff378420),
                                            fontFamily = Inter,
                                            fontWeight = FontWeight.Black)
                                    }
                                }
                            }
                            Text("Av. Diniz, 57  Jandira-SP",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                textAlign = TextAlign.End,
                                color = Color(0xff7E7E7E),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold)

                        }

                        Column (modifier = Modifier.padding(12.dp)){
                            Row (modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically ) {
                                Text(
                                    "Deixe seu comentário:",
                                    fontFamily = Inter,
                                    fontSize = 16.sp)
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable {
                                        navController.navigate("denuncia/${userIdFlow.value}")
                                    }) {
                                    Text(
                                        "Denunciar",
                                        modifier = Modifier.padding(end = 6.dp),
                                        color = Color(0xff9B0707),
                                        fontFamily = Inter,
                                        fontSize = 16.sp)
                                    Icon(Icons.Outlined.Warning, "", tint = Color(0xff9B0707))
                                }
                            }
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp)
                                    .height(120.dp),
                                shape = RectangleShape,
                                textStyle = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = Inter
                                ),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                ),
                                value = review,
                                onValueChange = {
                                    feedbackViewModel.setReview(it)
                                }
                            )
                        }


                    }
                }


                Button(
                    onClick = {

                        val avaliacao = userIdFlow.value?.let {
                            bico?.let { it1 ->
                                AvaliacaoUser(
//                                    id_cliente = bico.cliente[0].id,
                                    id_cliente = id_cliente.toInt(),
                                    id_usuario = it,
                                    id_bico = it1.id,
                                    avaliacao = review,
                                    nota = stars
                                )
                            }
                        }

                        val sendAvaliacao = avaliacao?.let {
                            RetrofitFactory()
                                .getFeedbackService()
                                .saveUser(it)
                        }


                        if (sendAvaliacao != null) {
                            sendAvaliacao.enqueue(object: Callback<AvaliacaoUser> {
                                override fun onResponse(call: Call<AvaliacaoUser>, res: Response<AvaliacaoUser>) {
                                    Log.i("Dados a serem enviados", avaliacao.toString())
                                    Log.i("Response: ", res.toString())
                                }

                                override fun onFailure(call: Call<AvaliacaoUser>, t: Throwable) {
                                    Log.i("Falhou:", t.toString())
                                }
                            })
                        }


                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainOrange
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text("Salvar", fontFamily = Inter, fontWeight = FontWeight.Black)
                }
        }
    }
}


private fun openImageChooser(){
    Intent(Intent.ACTION_PICK).also {
        it.type = "image/*"
        val mimeTypes = arrayOf("images/jpeg", "image/png")
    }
}


