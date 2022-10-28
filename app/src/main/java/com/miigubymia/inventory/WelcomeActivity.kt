package com.miigubymia.inventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.miigubymia.inventory.databinding.ActivityMainBinding
import com.miigubymia.inventory.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        // Animação
        val alphaAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.splash_anim)
        mainBinding.tvNameWelcome.startAnimation(alphaAnimation)

        // Criamos um obj handler para que no tempo programado ele feche a atividade e inicie a
        // próxima.
        val handler = Handler(Looper.getMainLooper())
        //Função do handler que vai esperar certo tempo para realizar o estabelecido
        // Como o runnable é uma interface vamos ter que usar o object para criar um objeto dela
        handler.postDelayed(object : Runnable{
            override fun run() {
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 4000)
    }
}