package org.d3if4079.hitungbmi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if4079.hitungbmi.R
import org.d3if4079.hitungbmi.data.KategoriBMI
import org.d3if4079.hitungbmi.databinding.FragmentHitungBinding


class HitungFragment : Fragment() {
    private val viewModel: HitungViewModel by viewModels()
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

            val selectedId = binding.radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()

            }


            val isMale = selectedId == R.id.priaRadioButton

            viewModel.hitungBmi(berat, tinggi, isMale)

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

        binding.shareButton.setOnClickListener {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            val gender = if (selectedId == R.id.priaRadioButton)
                getString(R.string.priaRadioButton)
            else
                getString(R.string.wanitaRadioButton)

            val message = getString(
                R.string.bagikan_template,
                binding.beratBadanEditText.text,
                binding.tinggiBadanEditText.text,
                gender,
                binding.bmiTextView.text,
                binding.kategoriTextView.text
            )

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT,message)
            if (shareIntent.resolveActivity(requireActivity().packageManager)!=null){
                startActivity(shareIntent)
            }


        }

        setHasOptionsMenu(true)
        return binding.root;

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHasilBmi().observe(viewLifecycleOwner, {
            if (it == null)  return@observe

            binding.bmiTextView.text = getString(R.string.bmi_x, it.bmi)
            binding.kategoriTextView.text = getString(R.string.kategori_x,getKategori(it.kategori) )
            binding.buttonGroup.visibility = View.VISIBLE


        }


        )
    }


    private fun getKategori(kategori : KategoriBMI): String {


        val stringRes = when (kategori) {

                KategoriBMI.Kurus -> R.string.kurus
                KategoriBMI.Gemuk -> R.string.gemuk
                KategoriBMI.Ideal -> R.string.ideal
            }
            return getString(stringRes)
        }
    }




