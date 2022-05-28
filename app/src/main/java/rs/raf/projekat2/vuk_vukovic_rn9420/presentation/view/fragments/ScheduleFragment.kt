package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        }
    }

    private fun initObservers(){
        subjectViewModel.subjectsState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        subjectViewModel.getAllSubjects()
        subjectViewModel.fetchSchedule()
    }

    private fun renderState(state: SubjectsState){
        when(state){
            is SubjectsState.Success -> {

            }
            is SubjectsState.Error -> {

            }
            is SubjectsState.DataFetched -> {

            }
            is SubjectsState.Loading -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}