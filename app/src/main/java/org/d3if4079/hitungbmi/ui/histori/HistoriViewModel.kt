package org.d3if4079.hitungbmi.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4079.hitungbmi.db.BmiDao
import org.d3if4079.hitungbmi.db.BmiDb

class HistoriViewModel(private  val db: BmiDao) : ViewModel() {
    val  data = db.getLastBmi()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.clearData()
        }
    }
}