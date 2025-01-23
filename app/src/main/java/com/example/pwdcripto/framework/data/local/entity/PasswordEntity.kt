package com.example.pwdcripto.framework.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pwdcripto.framework.contants.ConstantsDatabase


@Entity(tableName = ConstantsDatabase.TABLE_NAME )
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tag: String = "",
    val password: String = ""
)
