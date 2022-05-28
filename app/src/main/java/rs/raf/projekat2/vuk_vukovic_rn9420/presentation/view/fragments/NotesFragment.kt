package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentNotesBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.NoteAdapter

class NotesFragment:Fragment(R.layout.fragment_notes) {

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
        adapter = NoteAdapter()
        binding.notesRecycler.adapter = adapter

        //TODO CALLBACKS
    }

    private fun initListeners(){
        //todo
    }

    private fun initObservers(){
        //todo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}