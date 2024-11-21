package br.senai.sp.jandira.touccanuser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.touccanuser.model.Bico
import br.senai.sp.jandira.touccanuser.service.RetrofitFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AvBicoViewModel : ViewModel() {
    private val _bico = MutableStateFlow<Bico?>(null) // Estado encapsulado
    val bico: StateFlow<Bico?> = _bico // Exposto como leitura apenas

    private val _isLoading = MutableStateFlow(true) // Estado de carregamento
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow(false) // Estado de erro
    val error: StateFlow<Boolean> = _error

    fun fetchBico(bicoId: Int) {
        viewModelScope.launch {
            try {
                val result = RetrofitFactory()
                    .getBicoService()
                    .getBicoById(bicoId)
                    .execute() // Operação síncrona aqui

                if (result.isSuccessful) {
                    _bico.value = result.body()?.bico
                } else {
                    _error.value = true
                }
            } catch (e: Exception) {
                _error.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}