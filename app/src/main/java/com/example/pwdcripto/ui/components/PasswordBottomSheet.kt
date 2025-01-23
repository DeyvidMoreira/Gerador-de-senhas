package com.example.pwdcripto.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pwdcripto.framework.data.local.entity.PasswordEntity
import com.example.pwdcripto.ui.viewModels.PwdGeneratorViewModel

@Composable
fun PasswordBottomSheet(
    viewModel: PwdGeneratorViewModel,
    onDelete: (PasswordEntity) -> Unit
) {
    val passwords by viewModel.passwords.observeAsState(emptyList())

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    // Exibir BottomSheet logo que os dados forem carregados
    LaunchedEffect(passwords) {
        if (passwords.isNotEmpty()) {
            sheetState.show() // Garante que o BottomSheet seja mostrado
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {

        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(300.dp)
                .padding(0.dp)  // Remover qualquer padding extra que possa criar um espaço indesejado
        ) {
            items(passwords) { password ->
                PasswordItem(
                    password = password,
                    onDelete = { onDelete(password) }
                )
            }
        }
    }
}