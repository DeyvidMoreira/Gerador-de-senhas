package com.example.pwdcripto.framework.data.local

import androidx.room.migration.Migration
import com.example.pwdcripto.framework.contants.ConstantsDatabase

fun getDatabaseMigrations(): Array<Migration> {
    return arrayOf(
        // De 1 para 2
        Migration(1, 2) { database ->
            database.execSQL("ALTER TABLE ${ConstantsDatabase.TABLE_NAME} ADD COLUMN new_column TEXT")
        },
        // De 2 para 3
        Migration(2, 3) { database ->
            database.execSQL("CREATE TABLE IF NOT EXISTS `new_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT)")
        },
        // De 3 para 4
        Migration(3, 4) { database ->
            // Supondo que você adicionou uma coluna ou fez outra alteração na versão 4
            database.execSQL("ALTER TABLE ${ConstantsDatabase.TABLE_NAME} ADD COLUMN another_column TEXT")
        },
        // De 4 para 5
        Migration(4, 5) { database ->
            // Se você adicionar outra tabela ou alteração significativa na versão 5
            database.execSQL("CREATE TABLE IF NOT EXISTS `new_table_2` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `description` TEXT)")
        }
    )
}