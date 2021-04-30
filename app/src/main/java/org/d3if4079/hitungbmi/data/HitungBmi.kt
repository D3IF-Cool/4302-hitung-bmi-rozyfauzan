package org.d3if4079.hitungbmi.data

import org.d3if4079.hitungbmi.db.BmiEntity

object HitungBmi {

    fun hitung(data: BmiEntity): HasilBmi{

        val tinggiCm = data.tinggi / 100;
        val bmi = data.berat  / (tinggiCm * tinggiCm)
        val kategori = if (data.isMale)
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
        return HasilBmi(bmi, kategori)
    }

}