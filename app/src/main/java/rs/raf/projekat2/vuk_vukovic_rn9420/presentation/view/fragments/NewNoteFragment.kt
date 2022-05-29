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
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentNewNoteBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.AddNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.NoteViewModel

class NewNoteFragment : Fragment(R.layout.fragment_new_note){

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners(){
        binding.cancelNewButton.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.saveNewButton.setOnClickListener {
            val title = binding.newTitle.text.toString()
            val content = binding.newContent.text.toString()

            if(title != "" && content != ""){
                noteViewModel.insert(title, content)
            }
            else{
                Toast.makeText(context, "Polja ne smeju da budu prazna", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initObservers(){
        noteViewModel.addNoteState.observe(viewLifecycleOwner, Observer {
            handleState(it)
        })
    }

    private fun handleState(state: AddNoteState){
        when(state){
            is AddNoteState.Success -> {
                Toast.makeText(context, "Beleška uspešno dodata", Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
            is AddNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}