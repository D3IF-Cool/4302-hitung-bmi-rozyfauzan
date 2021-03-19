package org.d3if4079.hitungbmi.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if4079.hitungbmi.R
import org.d3if4079.hitungbmi.data.KategoriBMI
import org.d3if4079.hitungbmi.databinding.FragmentHitungBinding


class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding
    private lateinit var kategoriBMI: KategoriBMI

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_about){
            findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)

        binding.button.setOnClickListener {

            val berat = binding.beratBadanEditText.text.toString();
            if (TextUtils.isEmpty(berat)) {
                Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()

            }
            val tinggi = binding.tinggiBadanEditText.text.toString()
            if (TextUtils.isEmpty(tinggi)) {
                Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()

            }
            val tinggiCm = tinggi.toFloat() / 100;

            val selectedId = binding.radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()

            }


            val isMale = selectedId == R.id.priaRadioButton
            val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
            val kategori = getKategori(bmi, isMale)


            binding.bmiTextView.text = getString(R.string.bmi_x, bmi)
            binding.kategoriTextView.text = getString(R.string.kategori_x, kategori)
            binding.saranButton.visibility = View.VISIBLE

        }


        binding.buttonReset.setOnClickListener {
            binding.tinggiBadanEditText.text = null
            binding.beratBadanEditText.text = null
        }

        binding.saranButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
               HitungFragmentDirections.actionHitungFragmentToSaranFragment(kategoriBMI)
            )
        }
        setHasOptionsMenu(true)
        return binding.root;

    }


    private fun getKategori(bmi: Float, isMale: Boolean): String {
       kategoriBMI = if (isMale) {
            when {
                bmi < 20.5 -> KategoriBMI.Kurus
                bmi >= 27.5 ->  KategoriBMI.Gemuk
                else ->  KategoriBMI.Ideal
            }
        } else {
            when {
                bmi < 18.5 -> KategoriBMI.Kurus
                bmi >= 25.0 -> KategoriBMI.Gemuk
                else -> KategoriBMI.Ideal
            }
        }

        val stringRes = when (kategoriBMI) {

                KategoriBMI.Kurus -> R.string.kurus
                KategoriBMI.Gemuk -> R.string.gemuk
                KategoriBMI.Ideal -> R.string.ideal
            }
            return getString(stringRes)
        }
    }




