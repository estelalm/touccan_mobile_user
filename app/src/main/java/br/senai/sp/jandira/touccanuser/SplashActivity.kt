package br.senai.sp.jandira.touccanuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java)) // Substitua por sua Activity principal
        finish() // Fecha a Splash Screen
    }
}