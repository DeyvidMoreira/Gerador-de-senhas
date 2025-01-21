package com.example.pwdcripto.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pwdcripto.data.local.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePassword(vararg password: PasswordEntity)

    @Update
    fun updatePassword(vararg password: PasswordEntity)

    @Delete
    fun deletePassword(vararg password: PasswordEntity)

    @Query("SELECT * FROM password_table")
    fun getAllPasswords(): Flow<List<PasswordEntity>>


}