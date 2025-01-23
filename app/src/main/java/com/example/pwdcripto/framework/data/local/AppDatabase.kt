package com.example.pwdcripto.framework.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.pwdcripto.framework.contants.ConstantsDatabase
import com.example.pwdcripto.framework.data.local.dao.PasswordDao
import com.example.pwdcripto.framework.data.local.entity.PasswordEntity

@Database(entities = [PasswordEntity::class], version = ConstantsDatabase.VERSION_DATABASE)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
}