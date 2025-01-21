package com.example.pwdcripto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pwdcripto.di.appModule
import com.example.pwdcripto.ui.screens.PwdGeneratorScreen
import com.example.pwdcripto.ui.theme.PwdCriptoTheme
import com.example.pwdcripto.ui.viewModels.PwdGeneratorViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PwdGeneratorScreen()

        }
    }
}
