package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentEditNoteBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.AddNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.EditNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.NoteViewModel
import java.util.*

class EditNoteFragment(
    private val noteId: Int,
    private val noteTile: String,
    private val noteContent: String,
    private val noteArchived: Boolean,
    private val noteDate: Date
) : Fragment(R.layout.fragment_edit_note) {

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

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
        initObservers()
    }

    private fun initNote(){
        binding.editTitle.setText(noteTile)
        binding.editContent.setText(noteContent)
    }

    private fun initListeners(){
        binding.cancelEditButton.setOnClickListener {
            noteViewModel.setNeutral()
            activity?.onBackPressed()
        }

        binding.saveEditButton.setOnClickListener {
            val newTitle = binding.editTitle.text.toString()
            val newContent = binding.editContent.text.toString()

            if (newTitle != "" && newContent != ""){
                noteViewModel.update(noteId, newTitle, newContent, noteArchived, noteDate)
            }
            else{
                Toast.makeText(context, "Polja ne smeju da budu prazna", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservers(){
        noteViewModel.editNoteState.observe(viewLifecycleOwner, Observer {
            handleState(it)
        })
    }

    private fun handleState(state: EditNoteState){
        when(state){
            is EditNoteState.Success -> {
                Toast.makeText(context, "Beleška uspešno izmenjena", Toast.LENGTH_SHORT).show()
                noteViewModel.setNeutral()
                activity?.onBackPressed()
            }
            is EditNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> return
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}