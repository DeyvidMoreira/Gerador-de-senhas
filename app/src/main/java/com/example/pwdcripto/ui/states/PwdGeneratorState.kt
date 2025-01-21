package com.example.pwdcripto.ui.states

data class PwdGeneratorState(
    val upperChecked: Boolean = false,
    val lowChecked: Boolean = false,
    val numChecked: Boolean = false,
    val especialChecked: Boolean = false,
    val passwordLength: Int = 0,
    val sliderValue: Float = 8f,
    val generatedPassword: String? = null,
    val errorMessage: String? = null
)