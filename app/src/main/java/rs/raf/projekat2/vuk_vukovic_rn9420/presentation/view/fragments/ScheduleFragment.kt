package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.fragment_schedule.*
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentScheduleBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.subject.SubjectAdapter

class ScheduleFragment:Fragment() {

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

    private fun initObservers(){
        //TODO
    }
}