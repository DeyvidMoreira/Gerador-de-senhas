package com.example.pwdcripto.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pwdcripto.framework.contants.CharactersConstants
import com.example.pwdcripto.framework.contants.ConstantsMessages
import com.example.pwdcripto.framework.data.local.entity.PasswordEntity
import com.example.pwdcripto.framework.data.local.repository.PasswordRepository
import com.example.pwdcripto.ui.states.PwdGeneratorState
import com.example.pwdcripto.ui.until.PasswordValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PwdGeneratorViewModel(application: Application, private val passwordRepository: PasswordRepository) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(PwdGeneratorState())
    val state: StateFlow<PwdGeneratorState> = _state.asStateFlow()

    val passwords = passwordRepository.allPasswords.asLiveData()

    private val _searchQuery = MutableStateFlow("")


    val filteredPasswords = _searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            passwordRepository.allPasswords
        } else {
            passwordRepository.getPasswordsByTag(query)
        }
    }.asLiveData()

    fun updateOption(option: String, isChecked: Boolean) {
        _state.value = when (option) {
            "upper" -> _state.value.copy(upperChecked = isChecked)
            "lower" -> _state.value.copy(lowChecked = isChecked)
            "number" -> _state.value.copy(numChecked = isChecked)
            "special" -> _state.value.copy(especialChecked = isChecked)
            else -> _state.value
        }
    }

    fun updatePasswordLength(length: Int) {
        _state.value = _state.value.copy(passwordLength = length)
    }

    fun generatePassword() {
        val chars = buildString {
            if (_state.value.upperChecked) append(CharactersConstants.UPPER_CASE_COMPLETED)
            if (_state.value.lowChecked) append(CharactersConstants.LOWER_CASE_COMPLETED)
            if (_state.value.numChecked) append(CharactersConstants.NUMBERS)
            if (_state.value.especialChecked) append(CharactersConstants.SPECIAL_CHARACTERS)
        }

        if (chars.isEmpty()) {
            setError(ConstantsMessages.MESSAGE_NO_SELECTED_OPTION)
            return
        }

        if (_state.value.passwordLength < 1) {
            setError(ConstantsMessages.MESSAGE_NO_PASSWORD_LENGTH)
            return
        }

        val password = (1.._state.value.passwordLength)
            .map { chars.random() }
            .joinToString("")


        _state.value = _state.value.copy(
            generatedPassword = password,
            errorMessage = null
        )
    }

    fun savePassword(tag: String, password: String) {
        val error = PasswordValidator.validate(tag, _state.value.generatedPassword ?: "")
        val generatedPassword = _state.value.generatedPassword
        if (generatedPassword.isNullOrEmpty()) {
            setError(ConstantsMessages.MESSAGE_NO_GENERATE_PASSWORD)
            return
        }
        if (tag.isEmpty()) {
            setError(ConstantsMessages.MESSAGE_NO_TAG)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                passwordRepository.savePassword(
                    PasswordEntity(
                        tag = tag,
                        password = _state.value.generatedPassword ?: ""
                    )
                )
                setError(ConstantsMessages.MESSAGE_PASSWORD_SAVED)
            } catch (e: Exception) {
                Log.e("PwdGeneratorViewModel", ConstantsMessages.MESSAGE_PASSWORD_NOT_SAVED, e)
                setError(ConstantsMessages.MESSAGE_PASSWORD_NOT_SAVED)
            }
        }
    }

    // Função para buscar senhas filtradas pela tag
    fun getPasswordsByTag(query: String) {
        _searchQuery.value = query
    }

    // Função para deletar uma senha
    fun deletePassword(password: PasswordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            passwordRepository.deletePassword(password)
        }
    }


    private fun setError(message: String?) {
        _state.value = _state.value.copy(errorMessage = message)
        message?.let {
            viewModelScope.launch() {
                delay(3000) // Aguarda 3 segundos
                _state.value = _state.value.copy(errorMessage = null)
            }
        }
    }
}