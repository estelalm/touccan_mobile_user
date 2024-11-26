package br.senai.sp.jandira.touccanuser.screens

import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.touccanuser.MainActivity
import br.senai.sp.jandira.touccanuser.R
import br.senai.sp.jandira.touccanuser.model.AvaliacaoUser
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.model.Disponibilidade
import br.senai.sp.jandira.touccanuser.model.FeedbackUser
import br.senai.sp.jandira.touccanuser.model.ResultBico
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import br.senai.sp.jandira.touccanuser.ui.theme.Inter
import br.senai.sp.jandira.touccanuser.ui.theme.MainOrange
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.Period
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext


@Composable
fun UserProfile(navController: NavHostController, usuarioId: String, mainActivity: MainActivity) {
    var perfilUsuario = remember {
        mutableStateOf(UserPerfil())
    }
    var editState = remember {
        mutableStateOf(false)
    }

    val idUsuario = usuarioId.toInt()

    val callUserPerfil = RetrofitFactory()
        .getUserService()
        .getUserById(idUsuario)

    callUserPerfil.enqueue(object : Callback<ResultUserProfile> {
        override fun onResponse(p0: Call<ResultUserProfile>, p1: Response<ResultUserProfile>) {
            Log.i("response TelaC", p1.body()!!.toString())
            perfilUsuario.value = p1.body()!!.usuario
        }

        override fun onFailure(p0: Call<ResultUserProfile>, p1: Throwable) {
            Log.i("Falhou!!!", p1.toString())
        }
    })

    var idade = ""

    try {
        val data = perfilUsuario.value.data_nascimento.split("T")[0]
        Log.i("aa", data)
        idade = Period.between(LocalDate.now(), LocalDate.parse(data)).years.toString().replace("-", "")
    } catch (t: Throwable){
        Log.i("haha", t.toString())
    }

    var sobreNosState = remember {
        mutableStateOf(true)
    }

    var feedbackState = remember {
        mutableStateOf(false)
    }

    var imageUrl = remember { mutableStateOf<String?>(null) }
    var imageUri = remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            imageUri.value = uri
        }
    )
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFEBEBEB)) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .scrollable(rememberScrollState(0), orientation = Orientation.Vertical)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 4.dp, start = 14.dp, end = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.size(170.dp),
                    shape = CircleShape,
                    border = BorderStroke(5.dp, Color(0xffF07B07)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 9.dp)
                ) {

                    if (editState.value) { Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            imagePickerLauncher.launch("image/*")
                        }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.inserir_imagem),
                            "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                    }else{
                        var asyncModel = remember {
                            mutableStateOf("")
                        }
                        if (perfilUsuario.value.foto == "" || perfilUsuario.value.foto == null) {
                            asyncModel.value =
                                "https://i.pinimg.com/236x/21/9e/ae/219eaea67aafa864db091919ce3f5d82.jpg"
                        } else {
                            asyncModel.value = perfilUsuario.value.foto
                        }

                        AsyncImage(asyncModel.value, "", contentScale = ContentScale.FillWidth, alignment = Alignment.Center, modifier = Modifier.fillMaxSize())
                    }
                }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = perfilUsuario.value.nome + ", $idade",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                Text(
                    text = perfilUsuario.value.email,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
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
                                    text = "Sobre mim",
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
                            ),
                            enabled = !editState.value
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
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var formacaoState = remember {
                            mutableStateOf(if(perfilUsuario.value.formacao == ""){"Escolha a formação"
                            }else{perfilUsuario.value.formacao})
                        }
                        var bioState = remember {
                            mutableStateOf("Nenhuma biografia descrita ainda!")
                        }
                        bioState.value = perfilUsuario.value.biografia

                        var habilidadeState = remember {
                            mutableStateOf("Nenhuma habilidade descrita ainda!")
                        }
                        habilidadeState.value = perfilUsuario.value.habilidade

                        var disponibilidadeState = remember {
                            mutableStateOf(Disponibilidade(id = perfilUsuario.value.id_disponibilidade, disponibilidade = "Escolher"))
                        }

                        if (sobreNosState.value) {
                            UserInfo(editState, perfilUsuario, formacaoState, bioState, habilidadeState, disponibilidadeState)
                            Button(
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MainOrange
                                ),
                                modifier = Modifier
                                    .width(200.dp),
                                onClick = {
                                    if (editState.value) {
                                        val user = UserPerfil()


//                                        imageUri?.let { uri ->
//                                            coroutineScope.launch {
//                                                val uploadedUrl = uri.value?.let {
//                                                    uploadImageToCloudinary(
//                                                        it, context)
//                                                }
//                                                imageUrl.value = uploadedUrl
//
//
//
//                                            }
//                                        }

                                        perfilUsuario.value.nome = perfilUsuario.value.nome
                                        perfilUsuario.value.data_nascimento = perfilUsuario.value.data_nascimento.split("T")[0]
                                        perfilUsuario.value.biografia = bioState.value
                                        perfilUsuario.value.habilidade = habilidadeState.value
                                        perfilUsuario.value.id_disponibilidade = disponibilidadeState.value.id
                                        perfilUsuario.value.formacao = formacaoState.value
                                        perfilUsuario.value.foto = "https://static.todamateria.com.br/upload/ar/is/aristoteles-cke.jpg"
//                                        uploadedUrl?.let { perfilUsuario.value.foto = it }

                                        Log.i("dados a serem enviados", perfilUsuario.value.toString())


                                        Log.i("User:", perfilUsuario.value.toString() )

                                        val callUserPerfil = RetrofitFactory()
                                            .getUserService()
                                            .updateUser(perfilUsuario.value, idUsuario)

                                        callUserPerfil.enqueue(object : Callback<UserPerfil> {
                                            override fun onResponse(call: Call<UserPerfil>, res: Response<UserPerfil>) {
                                                Log.i("response edit", res.toString())
                                            }

                                            override fun onFailure(p0: Call<UserPerfil>, t: Throwable) {
                                                Log.i("Falhou!!!", t.toString())
                                            }
                                        })


                                    }
                                    editState.value = !editState.value
                                }
                            ) {
                                Text(
                                    if (editState.value) "Salvar alterações" else "Editar currículo",
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center,
                                    fontFamily = Inter,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(100.dp))

                        } else {
                            HistoryUser(perfilUsuario.value.id)
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun UserInfo(
        editState: MutableState<Boolean>,
        perfilUsuario: MutableState<UserPerfil>,
        formacaoState: MutableState<String>,
        bioState: MutableState<String>,
        habilidadeState: MutableState<String>,
        disponibilidadeState: MutableState<Disponibilidade>
    ) {

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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Formação: ",
                        fontFamily = Inter,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    TextField(
                        value = if (formacaoState.value != null) formacaoState.value else "",
                        onValueChange = {
                            formacaoState.value = it
                        },
                        enabled = editState.value,
                        textStyle = TextStyle(
                            fontFamily = Inter
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        "Biografia: ",
                        fontFamily = Inter,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    TextField(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        value = if(bioState.value != null) bioState.value else "",
                        onValueChange = {
                            bioState.value = it
                        },
                        enabled = editState.value,
                        textStyle = TextStyle(
                            fontFamily = Inter
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledPlaceholderColor = Color.Black,
                            focusedPlaceholderColor = Color.Black,
                            unfocusedPlaceholderColor = Color.Black
                        )
                    )
                }
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
                    Text(
                        "Habilidades: ",
                        fontFamily = Inter,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    TextField(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        value = habilidadeState.value,
                        onValueChange = {
                            habilidadeState.value = it
                        },
                        enabled = editState.value,
                        textStyle = TextStyle(
                            fontFamily = Inter
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
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

                val isDropDownExpanded = remember {
                    mutableStateOf(false)
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 2.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Disponibilidade: ",
                        fontFamily = Inter,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            if(editState.value){
                                isDropDownExpanded.value = true
                            }}

                    ) {

                            Text(disponibilidadeState.value.disponibilidade, fontFamily = Inter)
                            Image(
                                painter = painterResource(id = R.drawable.arrow_drop_down),
                                contentDescription = "DropDown Icon"
                            ) }

                    }
                    DropdownMenu(
                        expanded = isDropDownExpanded.value,
                        onDismissRequest = {
                            isDropDownExpanded.value = false
                        }) {

                        val itemPosition = remember {
                            mutableStateOf(0)
                        }


                        val disponibilidadeList = listOf(Disponibilidade(id = 1, disponibilidade = "Manhã"), Disponibilidade(id = 2, disponibilidade = "Tarde"), Disponibilidade(id = 3, disponibilidade = "Noite"))
                        disponibilidadeList.forEachIndexed { index, disp ->
                            DropdownMenuItem(text = {
                                Text(text = disp.disponibilidade)
                            },
                                onClick = {
                                    isDropDownExpanded.value = false
                                    itemPosition.value = index
                                    disponibilidadeState.value = disp
                                })
                        }
                    }
//                    TextField(
//                        value = disponibilidadeState.value,
//                        onValueChange = {
//                            disponibilidadeState.value = it
//                        },
//                        enabled = editState.value,
//                        textStyle = TextStyle(
//                            fontFamily = Inter
//                        ),
//                        colors = TextFieldDefaults.colors(
//                            unfocusedContainerColor = Color.Transparent,
//                            disabledContainerColor = Color.Transparent,
//                            focusedContainerColor = Color.Transparent,
//                            errorContainerColor = Color.Transparent,
//                            disabledIndicatorColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))


        }


@Composable
fun HistoryUser(userId: Int){

    var avaliacoesState = remember {
        mutableStateOf(listOf<AvaliacaoUser>())
    }
    var avaliacaoLoadState = remember {
        mutableStateOf(true)
    }

    val callFeedback = RetrofitFactory()
        .getFeedbackService()
        .getFeedbackUser(userId)

    callFeedback.enqueue(object : Callback<FeedbackUser> {
        override fun onResponse(p0: Call<FeedbackUser>, res: Response<FeedbackUser>) {
            Log.i("response feedback", res.body()!!.toString())
            avaliacoesState.value = res.body()!!.avaliacoes
            avaliacaoLoadState.value = false
        }

        override fun onFailure(p0: Call<FeedbackUser>, p1: Throwable) {
            Log.i("Falhou!!!", p1.toString())
        }
    })


    LazyColumn {

        if(avaliacaoLoadState.value){
            item { Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){ CircularProgressIndicator(color = MainOrange) } }
        }else{
            if(avaliacoesState.value.isEmpty()){
                item {
                    Text(
                    "Você ainda não foi avaliado!: ",
                    fontFamily = Inter,
                    color = Color.Black,
                        textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                ) }
            }else{
                items(avaliacoesState.value){ avaliacao ->

                    var bico = remember{ mutableStateOf( Bico())}

                    val callBico = RetrofitFactory()
                        .getBicoService()
                        .getBicoById(avaliacao.id_bico)

                    callBico.enqueue(object: Callback<ResultBico> {
                        override fun onResponse(call: Call<ResultBico>, res: Response<ResultBico>) {
                            Log.i("Response: ", res.toString())
                            bico.value = res.body()!!.bico
                        }

                        override fun onFailure(call: Call<ResultBico>, t: Throwable) {
                            Log.i("Falhou:", t.toString())
                        }
                    })


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
                                Text("${bico.value.cliente[0].nome_fantasia} - ${bico.value.titulo}.",
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Bold)
                                Text(avaliacao.avaliacao)
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 2.dp)){
                                    val stars = avaliacao.nota
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

    }

}


//
//suspend fun uploadImageToCloudinary(imageUri: Uri, context: Context): String? {
//    return withContext(Dispatchers.IO) {
//        try {
//            val cloudinary = Cloudinary(ObjectUtils.asMap(
//                "cloud_name", " dneuh2x7n",
//                "api_key", "482649795316662",
//                "api_secret", "LVUcSSkHp79gYWqB4zvbxUODh_g"
//            ))
//
//            // Converter a URI em caminho real
//            val file = File(getRealPathFromURI(imageUri, context))
//
//            // Fazer o upload da imagem
//            val uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap())
//
//            // Obter o URL público da imagem
//            uploadResult["secure_url"] as? String
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }
//}
//
//// Função para obter o caminho real do arquivo a partir da URI
//fun getRealPathFromURI(contentUri: Uri, context: Context): String {
//    val proj = arrayOf(MediaStore.Images.Media.DATA)
//    val cursor = context.contentResolver.query(contentUri, proj, null, null, null)
//    cursor?.moveToFirst()
//    val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//    val filePath = columnIndex?.let { cursor.getString(it) }
//    cursor?.close()
//    return filePath ?: ""
//}