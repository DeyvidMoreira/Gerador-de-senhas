package com.example.pwdcripto.framework.di

import androidx.room.Room
import com.example.pwdcripto.framework.contants.ConstantsDatabase
import com.example.pwdcripto.framework.data.local.AppDatabase
import com.example.pwdcripto.framework.data.local.until.getDatabaseMigrations
import com.example.pwdcripto.framework.data.local.repository.PasswordRepository
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
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().passwordDao() }
    single { PasswordRepository(get()) }

}