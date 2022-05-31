package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.internal.notifyAll
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentNotesBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.NoteAdapter
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.NoteDiffCallback
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.callback.NoteCallbackAction
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.ArchivedNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.NotesState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class NotesFragment:Fragment(R.layout.fragment_notes) {

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initRecycler(){
        binding.notesRecycler.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(NoteDiffCallback()){
            when(it.action){
                NoteCallbackAction.DELETE -> {
                    noteViewModel.deleteById(it.noteId!!)
                }
                NoteCallbackAction.EDIT -> {
                    clearSearch()

                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.addToBackStack(null)
                    transaction?.replace(R.id.fragmentContainerMain,
                        EditNoteFragment(it.noteId!!, it.noteTile!!, it.noteContent!!, it.noteArchive!!, it.noteDate!!))

                    transaction?.commit()
                }
                NoteCallbackAction.ARCHIVE -> {
                    Timber.e(it.noteArchive.toString())
                    noteViewModel.updateArchived(it.noteId!!, it.noteTile!!, it.noteContent!!, !it.noteArchive!!, it.noteDate!!, it.position)
                }
            }
        }
        binding.notesRecycler.adapter = adapter
    }

    private fun initListeners(){
        binding.newNoteButton.setOnClickListener {
            clearSearch()

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.fragmentContainerMain, NewNoteFragment())
            transaction?.commit()
        }

        binding.noteSearchEditText.doAfterTextChanged {
            val searchTag = it.toString()
            val archived = binding.archiveSwitch.isChecked
            noteViewModel.getByTitleOrContent(searchTag, archived)
        }

        binding.archiveSwitch.setOnClickListener {
            searchNotes()
        }
    }

    private fun initObservers(){
        noteViewModel.notesState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        noteViewModel.archivedNoteState.observe(viewLifecycleOwner) {
            Timber.e("EDIT NOTE STATE")
            searchNotes()
        }
        noteViewModel.deleteNoteState.observe(viewLifecycleOwner) {
            searchNotes()
        }
        noteViewModel.getAllNotes()
    }

    private fun renderState(state: NotesState) {
        when(state){
            is NotesState.Success -> {
                adapter.submitList(state.notes)
            }
            is NotesState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clearSearch(){
        binding.archiveSwitch.isChecked = true
        binding.noteSearchEditText.text.clear()
    }

    private fun searchNotes(){
        val searchTag = binding.noteSearchEditText.text.toString()
        val archived = binding.archiveSwitch.isChecked

        if(archived){
            if(searchTag != ""){
                noteViewModel.getByTitleOrContentSwitch(searchTag, true)
            }
            else{
                noteViewModel.getAllNotes()
            }
        }
        else{
            if(searchTag != ""){
                noteViewModel.getByTitleOrContentSwitch(searchTag, false)
            }
            else{
                noteViewModel.getOnlyUnarchivedNotes()
            }
        }
    }
}