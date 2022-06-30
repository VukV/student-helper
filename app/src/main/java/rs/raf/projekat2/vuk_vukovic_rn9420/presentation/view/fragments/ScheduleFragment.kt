package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentScheduleBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.SubjectContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.subject.SubjectAdapter
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.SubjectsState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.SubjectViewModel

class ScheduleFragment:Fragment(R.layout.fragment_schedule) {

    private val subjectViewModel: SubjectContract.ViewModel by sharedViewModel<SubjectViewModel>()

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SubjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinners()
        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initSpinners(){
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.groups_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.groupSpinner.adapter = adapter
            }
        }

        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.days_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.daySpinner.adapter = adapter
            }
        }
    }

    private fun initRecycler(){
        binding.scheduleRecycler.layoutManager = LinearLayoutManager(context)
        adapter = SubjectAdapter()
        binding.scheduleRecycler.adapter = adapter
    }

    private fun initListeners(){
        binding.searchButton.setOnClickListener {
            val searchTag = binding.searchEditText.text.toString()
            val group = binding.groupSpinner.selectedItem.toString()
            val day = binding.daySpinner.selectedItem.toString()

            if(searchTag != "" && group != "Grupa" && day != "Dan"){
                subjectViewModel.getAllByAllFilters(searchTag, group, day)
            }
            else if(searchTag == "" && group != "Grupa" && day != "Dan"){
                subjectViewModel.getAllByGroupAndDay(day, group)
            }
            else if(searchTag != "" && group == "Grupa" && day != "Dan"){
                subjectViewModel.getAllByNameOrProfessorAndDay(searchTag, day)
            }
            else if(searchTag != "" && group != "Grupa" && day == "Dan"){
                subjectViewModel.getAllByNameOrProfessorAndGroup(searchTag, group)
            }
            else if(searchTag == "" && group == "Grupa" && day != "Dan"){
                subjectViewModel.getAllByDay(day)
            }
            else if(searchTag == "" && group != "Grupa" && day == "Dan"){
                subjectViewModel.getAllByGroup(group)
            }
            else if(searchTag != "" && group == "Grupa" && day == "Dan"){
                subjectViewModel.getAllByNameOrProfessor(searchTag)
            }
        }
    }

    private fun initObservers(){
        subjectViewModel.subjectsState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        subjectViewModel.getAllSubjects()
        subjectViewModel.fetchSchedule()
    }

    private fun renderState(state: SubjectsState){
        when(state){
            is SubjectsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.subjects)
            }
            is SubjectsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is SubjectsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Podaci pokupljeni sa servera", Toast.LENGTH_LONG).show()
            }
            is SubjectsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.progressBar.isVisible = loading
        binding.searchEditText.isEnabled = !loading
        binding.searchButton.isEnabled = !loading
        binding.daySpinner.isEnabled = !loading
        binding.groupSpinner.isEnabled = !loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}