package com.example.pwdcripto.di

import androidx.room.Room
import com.example.pwdcripto.contants.ConstantsDatabase
import com.example.pwdcripto.data.local.AppDatabase
import com.example.pwdcripto.data.local.getDatabaseMigrations
import com.example.pwdcripto.data.local.repository.PasswordRepository
import com.example.pwdcripto.ui.viewModels.PwdGeneratorViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    viewModelOf(::PwdGeneratorViewModel)

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            ConstantsDatabase.DATA_BASE_NAME
        )
            .addMigrations(*getDatabaseMigrations())
            .build()
    }

    single { get<AppDatabase>().passwordDao() }
    single { PasswordRepository(get()) }

}