package org.d3if4079.hitungbmi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if4079.hitungbmi.R
import org.d3if4079.hitungbmi.data.KategoriBMI
import org.d3if4079.hitungbmi.databinding.FragmentSaranBinding


class SaranFragment : Fragment() {
    private val args: SaranFragmentArgs by navArgs()
    private lateinit var binding: FragmentSaranBinding
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        updateUI(args.kategori)
        return binding.root;
    }

    private fun updateUI(kategori : KategoriBMI){
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when(kategori){
            KategoriBMI.Kurus->{
                actionBar?.title = getString(R.string.judul_kurus)
                binding.gambarkondisiImageView.setImageResource(R.drawable.kurus)
                binding.deksripsikondisiTextView.text = getString(R.string.saran_kurus)


            }

            KategoriBMI.Ideal->{
                actionBar?.title = getString(R.string.judul_ideal)
                binding.gambarkondisiImageView.setImageResource(R.drawable.ideal)
                binding.deksripsikondisiTextView.text = getString(R.string.saran_ideal)


            }

            KategoriBMI.Gemuk->{
            actionBar?.title = getString(R.string.judul_gemuk)
            binding.gambarkondisiImageView.setImageResource(R.drawable.gemuk)
            binding.deksripsikondisiTextView.text = getString(R.string.saran_gemuk)

               }
        }





    }




}