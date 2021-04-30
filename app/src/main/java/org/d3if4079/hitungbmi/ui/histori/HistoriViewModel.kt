package org.d3if4079.hitungbmi.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if4079.hitungbmi.db.BmiDao
import org.d3if4079.hitungbmi.db.BmiDb

class HistoriViewModel(db: BmiDao) : ViewModel() {
    val  data = db.getLastBmi()
}