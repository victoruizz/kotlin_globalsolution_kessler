package br.com.fiap.kessler.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.fiap.kessler.data.model.Debris
import br.com.fiap.kessler.data.model.RiskLevel
import br.com.fiap.kessler.data.repository.DebrisRepository

class CatalogViewModel : ViewModel() {

    private val allDebris: List<Debris> = DebrisRepository.getAll()

    var selectedRisk by mutableStateOf<RiskLevel?>(null)
        private set

    fun selectRisk(risk: RiskLevel?) {
        selectedRisk = risk
    }

    fun getFilteredList(): List<Debris> {
        val current = selectedRisk
        return if (current == null) allDebris else allDebris.filter { it.riskLevel == current }
    }
}
