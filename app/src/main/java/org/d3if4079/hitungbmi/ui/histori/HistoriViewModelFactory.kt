package org.d3if4079.hitungbmi.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if4079.hitungbmi.db.BmiDao
import org.d3if4079.hitungbmi.ui.hitung.HitungViewModel
import java.lang.IllegalArgumentException

class HistoriViewModelFactory(private val db: BmiDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HistoriViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
