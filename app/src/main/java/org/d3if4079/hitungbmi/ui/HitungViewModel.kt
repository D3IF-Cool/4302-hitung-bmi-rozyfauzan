package org.d3if4079.hitungbmi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if4079.hitungbmi.R
import org.d3if4079.hitungbmi.data.HasilBmi
import org.d3if4079.hitungbmi.data.KategoriBMI

class HitungViewModel : ViewModel() {
    private val hasilBmi = MutableLiveData<HasilBmi?>()

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

 }

    fun getHasilBmi() : LiveData<HasilBmi?> = hasilBmi



}




