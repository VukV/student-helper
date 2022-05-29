package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.StatsNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class StatsFragment: Fragment() {

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()
    private var days by mutableStateOf(listOf<Int>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                setupStats()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getLastFiveDays()
    }

    override fun onPause() {
        super.onPause()
        noteViewModel.clearStats()
    }

    @Composable
    private fun setupStats(){
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ){
            Text(
                text = "Statistika",
                fontSize = 30.sp
            )
            Text(
                text = "Beleške poslednjih 5 dana",
                fontSize = 15.sp
            )

            Row(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {

            }
        }
    }

    private fun initObservers(){
        noteViewModel.statsNoteState.observe(viewLifecycleOwner, Observer {
            renderStats(it)
        })
    }

    private fun renderStats(state: StatsNoteState){
        when(state){
            is StatsNoteState.Success -> {
                days = listOf(1, 4, 5)
                Timber.e(state.notes.toString())
            }
            is StatsNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> return
        }
    }
}