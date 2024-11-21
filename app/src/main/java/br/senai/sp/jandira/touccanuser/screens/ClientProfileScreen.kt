package br.senai.sp.jandira.touccanuser.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.UserPreferences
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.Candidatos
import br.senai.sp.jandira.touccanuser.model.ClienteId
import br.senai.sp.jandira.touccanuser.model.ClientePerfil
import br.senai.sp.jandira.touccanuser.model.Endereco
import br.senai.sp.jandira.touccanuser.model.ResultBicos
import br.senai.sp.jandira.touccanuser.model.ResultBicosPremium
import br.senai.sp.jandira.touccanuser.model.ResultCandidatos
import br.senai.sp.jandira.touccanuser.model.ResultClientProfile
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.senai.sp.jandira.touccanuser.screens.SobreNos as SobreNos

@Composable
fun ClientProfile(navController: NavHostController,  idCliente: String, mainActivity: MainActivity) {

   val clienteId = idCliente.toInt()
    Log.i("ID CLIENTE TELA PERFIL C", clienteId.toString())

    var perfilCliente = remember {
        mutableStateOf(ClientePerfil())
    }

    val callClientPerfil = RetrofitFactory()
        .getClientService()
        .getClientById(clienteId)

    callClientPerfil.enqueue(object: Callback<ResultClientProfile> {
        override fun onResponse(p0: Call<ResultClientProfile>, p1: Response<ResultClientProfile>) {
            Log.i("response TelaC", p1.body()!!.toString())
            perfilCliente.value = p1.body()!!.cliente
        }

        override fun onFailure(p0: Call<ResultClientProfile>, p1: Throwable) {
            Log.i("Falhou!!!", p1.toString())
        }

    })

    var sobreNosState = remember{
        mutableStateOf(false)
    }

    var feedbackState = remember{
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 4.dp, start = 14.dp, end = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
//                Log.i("ID BACK HOME", LoginResult(UserId()).toString()

                Image(
                    painter = painterResource(R.drawable.seta_voltar),
                    contentDescription = "",
                    modifier = Modifier
                        .width(30.dp)
                        .clickable { navController.popBackStack() }
                )
                Image(
                    painter = painterResource(R.drawable.logo_touccan),
                    contentDescription = "",
                    modifier = Modifier.size(height = 86.dp, width = 150.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.size(170.dp),
                    shape = CircleShape,
                    border = BorderStroke(5.dp, Color(0xffF07B07)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 9.dp)
                ) {
                    AsyncImage(
                        model = "",
                        if (perfilCliente.value.foto == "" || perfilCliente.value.foto == null) "https://pin.it/36EuigXZ6"
                        else perfilCliente.value.foto,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

//                    Image(
//                        painter = painterResource(R.drawable.cliente_pfp),
//                        contentDescription = "",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.FillBounds
//                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    text = perfilCliente.value.nome_fantasia,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    //.padding(top = 4.dp, start = 14.dp, end = 14.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            feedbackState.value = false
                            sobreNosState.value = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Sobre nós",
                                textAlign = TextAlign.Center,
                                fontFamily = Inter,
                                fontStyle = if (sobreNosState.value) {
                                    FontStyle.Italic
                                } else {
                                    FontStyle.Normal
                                },
                                fontWeight = FontWeight.SemiBold,
                                color = if (sobreNosState.value) {
                                    Color.Black
                                } else {
                                    Color(0xffC6C5C5)
                                }
                            )
                            if (sobreNosState.value) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xffF07B07))
                                        .height(1.dp)
                                        .width(100.dp)

                                )
                            }
                        }
                    }
                    Button(
                        onClick = {
                            feedbackState.value = true
                            sobreNosState.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Feedback",
                                textAlign = TextAlign.Center,
                                fontFamily = Inter,
                                fontStyle = if (feedbackState.value) {
                                    FontStyle.Italic
                                } else {
                                    FontStyle.Normal
                                },
                                fontWeight = FontWeight.SemiBold,
                                color = if (feedbackState.value) {
                                    Color.Black
                                } else {
                                    Color(0xffC6C5C5)
                                }
                            )
                            if (feedbackState.value) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xffF07B07))
                                        .height(1.dp)
                                        .width(100.dp)

                                )
                            }
                        }
                    }

                }
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (sobreNosState.value) SobreNos(perfilCliente,navController, mainActivity)
                    else Feedback()
                }
            }
        }
    }
}


@Composable
fun SobreNos(clientePerfil: MutableState<ClientePerfil>, navController: NavHostController, context: Context){

    var enderecoCliente = remember {
        mutableStateOf(Endereco())
    }

    var bicosList = remember {
        mutableStateOf(listOf<Bico>())
    }
    var isLoadingState = remember{
        mutableStateOf(true)
    }
    var errorState = remember{
        mutableStateOf(true)
    }

    val cliente = ClienteId(id_cliente = clientePerfil.value.id)

    val callBicoList = RetrofitFactory()
        .getBicoService()
        .getBicosByCLiente(cliente)

    callBicoList.enqueue(object: Callback<ResultBicosPremium>{
        override fun onResponse(call: Call<ResultBicosPremium>, res: Response<ResultBicosPremium>) {
            val bicos = res.body()?.bico
            if(bicos != null){
                bicosList.value = bicos
            }else{
                Log.i("Error: ", "A lista de bicos retornou nula")
            }
            isLoadingState.value = false
        }

        override fun onFailure(call: Call<ResultBicosPremium>, t: Throwable) {
            Log.i("Falhou:", t.toString())
            errorState.value = true
        }
    })



//    val callEndereco = RetrofitFactory()
//        .getEnderecoService()
//        .getViaCep(clientePerfil.value.cep)
//
//    callEndereco.enqueue(object: Callback<Endereco> {
//        override fun onResponse(p0: Call<Endereco>, res: Response<Endereco>) {
//            Log.i("response:", res.body()!!.toString())
//            enderecoCliente.value = res.body()!!
//        }
//
//        override fun onFailure(p0: Call<Endereco>, t: Throwable) {
//            Log.i("Falhou!!!", t.toString())
//        }
//
//    })

    Column(
        modifier = Modifier.padding(vertical = 24.dp, horizontal = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    "Endereço: ",
                    fontFamily = Inter,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Rua numero cep endereco ",
                    fontFamily = Inter,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize()
            ) {
                Column {

                    Text(
                        "Telefone: +55 ${clientePerfil.value.telefone}",
                        fontFamily = Inter,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Email: ${clientePerfil.value.email}",
                        fontFamily = Inter,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Anúncios",
                textAlign = TextAlign.Center,
                fontFamily = Inter,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black

            )
                Box(
                    modifier = Modifier
                        .background(Color(0xffF07B07))
                        .height(1.dp)
                        .width(100.dp)

                )
        }
        LazyColumn {
            items(bicosList.value){ bico ->
                BicoCard(bico, navController, context)
            }

        }
}



}
@Composable
fun BicoCard(bico: Bico, navController: NavHostController, context: Context) {

    val userPreferences = UserPreferences(context)
    val userIdFlow = userPreferences.userId.collectAsState(initial = null)
    val user = userIdFlow.value

    var candidateList = remember {
        mutableStateOf(listOf<Candidatos>())
    }

    val callCandidateList = RetrofitFactory()
        .getBicoService()
        .getCandidatosByBico(bico.id)

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
            if (item.id_candidato == user){
                candidatado.value = true
            }
        }
    }else{

    }

    val grayColor = 0xff6D6D6D
    val cinzaEscuro = 0xff888888

    Row (modifier = Modifier.padding(16.dp)){
        Column (
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
        ){
            Card (modifier = Modifier.clickable { navController.navigate("bico/${bico.id}/${candidatado.value}") }){
                Row (modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color.White)){
                    Card (
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xffF07B07),
                        ),
                        shape = RectangleShape
                    ){}
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        Text(
                            text = bico.titulo,
                            fontFamily = Inter,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(grayColor)
                        )
                        Text(
                            text = bico.descricao,
                            fontFamily = Inter,
                            fontSize = 15.sp,
                            lineHeight = 15.sp,
                            color = Color(cinzaEscuro)
                        )
                        Column {
                            Text(
                                text = "Jandira - SP",
                                fontFamily = Inter,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(grayColor)
                            )
                        }

                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text ="R$" + bico.salario,
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 12.sp,
                                color = Color(0xffF07B07)
                            ) }
                    }
                }
            }

        }
    }

}


@Composable
fun Feedback(){
    LazyColumn {
        items(3){
            ElevatedCard (modifier = Modifier
                .clickable { }
                .padding(horizontal = 18.dp, vertical = 8.dp),
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
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text("Usuario tal - Assistente Admnistrativo.",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold)
                        Text("Ótimo trabalho")
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp)){
                            val stars = 3
                            for (i in 1..5) {
                                if(i <= stars) Icon(Icons.Filled.Star,contentDescription = "", tint = Color(0xFFFFBC06))
                                else
                                    Icon(Icons.Filled.Star,contentDescription = "", tint = Color(0xFF504D4D))
                            }

                        }
                    }

                }
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun ClientProfilePreview(){
//    Surface (modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
//      ClientProfile(navController)
//    }
//}