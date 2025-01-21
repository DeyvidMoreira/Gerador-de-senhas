package com.example.pwdcripto.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pwdcripto.contants.ConstantsDatabase


@Entity(tableName = ConstantsDatabase.TABLE_NAME )
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val tag : String = "",
    val password : String = ""
)
