package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    //TODO konstruktor prima Note koji se menja

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNote()
        initListeners()
    }

    private fun initListeners(){
        //todo
    }

    private fun initNote(){
        //todo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}