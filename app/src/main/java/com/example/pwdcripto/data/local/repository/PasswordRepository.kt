package com.example.pwdcripto.data.local.repository

import com.example.pwdcripto.data.local.dao.PasswordDao
import com.example.pwdcripto.data.local.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

class PasswordRepository(private val passwordDao: PasswordDao) {

    fun getAllPasswords(): Flow<List<PasswordEntity>> = passwordDao.getAllPasswords()

    suspend fun savePassword(password: PasswordEntity) = passwordDao.savePassword(password)
    suspend fun updatePassword(password: PasswordEntity) = passwordDao.updatePassword(password)
    suspend fun deletePassword(password: PasswordEntity) = passwordDao.deletePassword(password)




}