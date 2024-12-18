package br.senai.sp.jandira.touccanuser.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.Candidato
import br.senai.sp.jandira.touccanuser.model.Candidatos
import br.senai.sp.jandira.touccanuser.model.ClientePerfil
import br.senai.sp.jandira.touccanuser.model.ResultBicos
import br.senai.sp.jandira.touccanuser.model.ResultBicosPremium
import br.senai.sp.jandira.touccanuser.model.ResultCandidatos
import br.senai.sp.jandira.touccanuser.model.ResultClientProfile
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavHostController,
    idUser: Int?,
    mainActivity: MainActivity,
) {

    var perfilUsuario = remember {
        mutableStateOf(UserPerfil())
    }

    val callUserPerfil = idUser?.let {
        RetrofitFactory()
        .getUserService()
        .getUserById(it)
    }

    if (callUserPerfil != null) {
        callUserPerfil.enqueue(object : Callback<ResultUserProfile> {
            override fun onResponse(p0: Call<ResultUserProfile>, p1: Response<ResultUserProfile>) {
                perfilUsuario.value = p1.body()!!.usuario
            }

            override fun onFailure(p0: Call<ResultUserProfile>, p1: Throwable) {
                Log.i("Falhou!!!", p1.toString())
            }
        })
    }


    val id = idUser.toString()

    var bicosList = remember {
        mutableStateOf(listOf<Bico>())
    }
    var isLoadingPertoDeVoce = remember{
        mutableStateOf(true)
    }
    var errorPertoDeVoce = remember {
        mutableStateOf(false)
    }
    var errorPertoDeVoceMessage = remember {
        mutableStateOf("Não há bicos perto de você!")
    }


    var isLoadingUrgente = remember{
        mutableStateOf(true)
    }
    var urgenteList = remember {
        mutableStateOf(listOf<Bico>())
    }
    var errorUrgente = remember {
        mutableStateOf(false)
    }


    val cep = if(perfilUsuario.value.cep.length != 8){"0"+perfilUsuario.value.cep}else{perfilUsuario.value.cep}
    val callBicoList = RetrofitFactory()
        .getBicoService()
        .getBicoByCep(cep)

    callBicoList.enqueue(object: Callback<ResultBicos>{
        override fun onResponse(call: Call<ResultBicos>, res: Response<ResultBicos>) {
            val bicos = res.body()?.bicos
            isLoadingPertoDeVoce.value = false
            if(bicos != null){
                bicosList.value = bicos
            }else{
                Log.i("Error: ", "A lista de bicos retornou nula")
            }
        }

        override fun onFailure(call: Call<ResultBicos>, t: Throwable) {
            Log.i("Falhou:", t.toString())
            isLoadingPertoDeVoce.value = false
            errorPertoDeVoce.value = true
        }
    })
    val callUrgenteList = RetrofitFactory()
        .getBicoService()
        .getBicosPremium()

    callUrgenteList.enqueue(object: Callback<ResultBicosPremium>{
        override fun onResponse(call: Call<ResultBicosPremium>, res: Response<ResultBicosPremium>) {
            val bicos = res.body()?.bico
            Log.i("resposta urgente: ", res.body().toString())
            if(bicos != null){
                urgenteList.value = bicos
            }else{
                Log.i("Error: ", "A lista de bicos retornou nula")
            }
            isLoadingUrgente.value = false
        }

        override fun onFailure(call: Call<ResultBicosPremium>, t: Throwable) {
            Log.i("Falhou:", t.toString())
            isLoadingUrgente.value = false
            errorUrgente.value = true
        }
    })


    val laranja = 0xffF07B07

    val cinza = 0xffC6C5C5

    var pertoDeVoceState = remember{
        mutableStateOf(false)
    }

    var urgenteState = remember{
        mutableStateOf(true)
    }

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        topBar = {

            br.senai.sp.jandira.touccanuser.utility.TopAppBar(navController, mainActivity)
        },
        bottomBar = {
            br.senai.sp.jandira.touccanuser.utility.BottomAppBar(navController, mainActivity)
        }
    ) { innerpadding ->
        Column (Modifier.padding(vertical = 100.dp)){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround

            ){
                Button(
                    onClick = {
                        urgenteState.value = false
                        pertoDeVoceState.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {

                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Perto de você",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = if(pertoDeVoceState.value) {FontStyle.Italic}
                            else {FontStyle.Normal},
                            fontWeight = FontWeight.SemiBold,
                            color = if(pertoDeVoceState.value) {Color.Black }
                            else {Color(cinza)}
                        )
                        if(pertoDeVoceState.value){
                            Box(
                                modifier = Modifier
                                    .background(Color(laranja))
                                    .height(1.dp)
                                    .width(100.dp)

                            )
                        }
                    }

                }
                Button(onClick = {
                    urgenteState.value = true
                    pertoDeVoceState.value = false
                },
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "Urgente",
                            textAlign = TextAlign.Center,
                            fontFamily = Inter,
                            fontStyle = if(urgenteState.value) {FontStyle.Italic}
                            else {FontStyle.Normal},
                            fontWeight = FontWeight.SemiBold,
                            color = if(urgenteState.value) {Color.Black }
                                    else {Color(cinza)}
                        )
                        if(urgenteState.value){
                            Box(
                                modifier = Modifier
                                    .background(Color(laranja))
                                    .height(1.dp)
                                    .width(100.dp)

                            )
                        }
                    }
                }
            }
            LazyColumn (contentPadding = PaddingValues(0.dp)){
                var isPremium = false
                if(pertoDeVoceState.value){
                    if (isLoadingPertoDeVoce.value) {
                        item(){
                            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){ CircularProgressIndicator(color = Color(laranja)) }
                        }
                    }else{
                        if(bicosList.value.isNotEmpty()){
                            items(bicosList.value){bico ->

                                if (idUser != null) {
                                    AnuncioCard(bico, navController, idUser, mainActivity, isPremium)
                                }
                            }
                        }else{
                            item(){ Text(text = errorPertoDeVoceMessage.value, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)}
                        }

                    }

                }else{
                    if (isLoadingUrgente.value) {
                        item(){
                            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){ CircularProgressIndicator(color = Color(laranja)) }
                        }
                    }else{
                        if(errorUrgente.value){
                            item{Text(text = "Não foi possível encontrar resultados, tente novamente mais tarde.", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)}
                        }else{
                            items(urgenteList.value){bico ->
                                isPremium = true
                                if (idUser != null) {
                                    AnuncioCard(bico, navController, idUser, mainActivity, isPremium)
                                }
                            }
                        }

                    }

                    }
                }

            }
        }

    }


@Composable
fun AnuncioCard(
    bico: Bico,
    navController: NavHostController,
    user: Int,
    mainActivity: MainActivity,
    isPremium: Boolean,
) {

    var perfilCliente = remember {
        mutableStateOf(ClientePerfil())
    }

    val callClientPerfil = RetrofitFactory()
        .getClientService()
        .getClientById(bico.cliente[0].id)

    callClientPerfil.enqueue(object: Callback<ResultClientProfile> {
        override fun onResponse(p0: Call<ResultClientProfile>, p1: Response<ResultClientProfile>) {
            Log.i("response TelaC", p1.body()!!.toString())
            perfilCliente.value = p1.body()!!.cliente
        }

        override fun onFailure(p0: Call<ResultClientProfile>, p1: Throwable) {
            Log.i("Falhou!!!", p1.toString())
        }

    })

    val candidatou = remember{
        mutableStateOf(false)
    }

    var candidateList = remember {
        mutableStateOf(listOf<Candidatos>())
    }
    var candidatadoState = remember{
        mutableStateOf("Candidatar-se")
    }

    var toastMessageState = remember{
        mutableStateOf("")
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
    val greenColor = 0xff106B16
    val cinzaEscuro = 0xff888888

    Row (modifier = Modifier.padding(16.dp)){
        ElevatedCard (
            modifier = Modifier.size(35.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(50.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 40.dp
            )
        ){
            var asyncModel = remember {
                mutableStateOf("")
            }
            if (perfilCliente.value.foto == "" || perfilCliente.value.foto == null) {
                asyncModel.value =
                    "https://i.pinimg.com/236x/21/9e/ae/219eaea67aafa864db091919ce3f5d82.jpg"
            } else {
                asyncModel.value = perfilCliente.value.foto
            }

            AsyncImage(asyncModel.value, "", contentScale = ContentScale.FillWidth, alignment = Alignment.Center, modifier = Modifier.fillMaxSize())
        }
        Column (
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
        ){
            ClickableText(
                text = AnnotatedString(bico.cliente[0].nome_fantasia),
                modifier = Modifier.padding(bottom = 10.dp, start = 4.dp),
                style = TextStyle(
                    fontFamily = Inter,
                    color = Color(cinzaEscuro),
                    fontWeight = FontWeight.Normal
                ),
                onClick = {
                    navController.navigate("perfilCliente/${bico.cliente[0].id}")
                }
            )
            Card (modifier = Modifier.clickable {
                if(candidatou.value){
                    navController.navigate("meuBico/${bico.id}/${candidatado.value}")
                }else{if(candidatado.value){
                    navController.navigate("meuBico/${bico.id}/${candidatado.value}")
                }else{
                    navController.navigate("bico/${bico.id}/${candidatado.value}")
                }}

            }){
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
                        Row {
                            Text(
                                text = bico.titulo,
                                fontFamily = Inter,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(grayColor)
                            )
                            if(isPremium){
                                Icon(
                                    painter = painterResource(R.drawable.fogo), "Fogo", tint = MainOrange
                                )
                            }

                        }

                        Text(
                            text = bico.descricao,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
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
                            Row {
                                Text(
                                    text = "Dificuldade: ",
                                    fontFamily = Inter,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(grayColor)
                                )
                                Text(
                                    text = "Baixa",
                                    fontFamily = Inter,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(greenColor)
                                )
                            }
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
                            )
                            Button(
                            modifier = Modifier.height(32.dp),
                                enabled = if(candidatou.value){false}else{if(candidatado.value){false}else{true}},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(candidatou.value){Color(grayColor)}else{if(candidatado.value){Color(grayColor)}else{Color(0xffF07B07)}},
                                disabledContentColor = Color(grayColor)

                            ),
                            shape = RoundedCornerShape(14.dp),
                            onClick = {
                                val candidato = Candidato(
                                    id_bico = bico.id,
                                    id_user = user
                                )

                                val postCandidato = RetrofitFactory()
                                    .getBicoService()
                                    .postCandidato(candidato)

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
                        ) {

                            Text(
                                text = if(candidatou.value){"Candidatado"}else{ if(candidatado.value){"Candidatado"}else{"Candidatar-se"}},
                                fontFamily = Inter,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 12.sp,
                                color = Color.White
                            )
                        } }
                    }
                }
            }

        }
    }

}

