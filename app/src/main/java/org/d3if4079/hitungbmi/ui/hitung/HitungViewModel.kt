package org.d3if4079.hitungbmi.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4079.hitungbmi.data.HasilBmi
import org.d3if4079.hitungbmi.data.KategoriBMI
import org.d3if4079.hitungbmi.db.BmiDao
import org.d3if4079.hitungbmi.db.BmiDb
import org.d3if4079.hitungbmi.db.BmiEntity

class HitungViewModel(private val db: BmiDao) : ViewModel() {

    private val hasilBmi = MutableLiveData<HasilBmi?>()

    private val  navigasi = MutableLiveData<KategoriBMI?>()

    val data = db.getLastBmi()

    fun hitungBmi(berat : String , tinggi:String, isMale : Boolean){

    val tinggiCm = tinggi.toFloat() / 100;
    val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
    val kategori = if (isMale)
    {
        when {
            bmi < 20.5 -> KategoriBMI.Kurus
            bmi >= 27.5 -> KategoriBMI.Gemuk
            else -> KategoriBMI.Ideal
        }
    } else
    {
        when {
            bmi < 18.5 -> KategoriBMI.Kurus
            bmi >= 25.0 -> KategoriBMI.Gemuk
            else -> KategoriBMI.Ideal
        }
    }

        hasilBmi.value = HasilBmi(bmi,kategori)


        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val dataBmi = BmiEntity(berat= berat.toFloat(), tinggi = tinggi.toFloat(),isMale = isMale)
                db.insert(dataBmi)
            }
        }

 }

    fun mulaiNavigasi(){
        navigasi.value = hasilBmi.value?.kategori
    }

    fun selesaiNavigasi(){
        navigasi.value = null
    }



    fun getHasilBmi() : LiveData<HasilBmi?> = hasilBmi

    fun getNavigasi() : LiveData<KategoriBMI?> = navigasi



}




