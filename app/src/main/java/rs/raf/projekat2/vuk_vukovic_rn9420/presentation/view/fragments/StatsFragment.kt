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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note.Note
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract.NoteContract
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.states.StatsNoteState
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.viewmodel.NoteViewModel
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random

class StatsFragment: Fragment() {

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()
    private var days by mutableStateOf(sortedMapOf(Pair("", 0)))

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
                fontSize = 20.sp
            )

            BarChartView()
        }
    }

    @Composable
    fun BarChartView() {
        if(days.isEmpty()){
            return
        }
        val barList:MutableList<BarChartData.Bar> = ArrayList()
        days.forEach{
            barList.add(
                BarChartData.Bar(
                    label = it.key,
                    value = it.value.toFloat(),
                    color = Color(0xFF0099BC)
                )
            )
        }

        BarChart(
            barChartData = BarChartData(bars = barList),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp),
            animation = simpleChartAnimation(),
            barDrawer = SimpleBarDrawer(),
            xAxisDrawer = SimpleXAxisDrawer(),
            yAxisDrawer = SimpleYAxisDrawer(drawLabelEvery = 5),
            labelDrawer = SimpleLabelDrawer(drawLocation = SimpleLabelDrawer.DrawLocation.XAxis)
        )
    }

    private fun initObservers(){
        noteViewModel.statsNoteState.observe(viewLifecycleOwner, Observer {
            renderStats(it)
        })
    }

    private fun renderStats(state: StatsNoteState){
        when(state){
            is StatsNoteState.Success -> {
                days = filterDates(state.notes)
                Timber.e(state.notes.toString())
            }
            is StatsNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> return
        }
    }

    private fun filterDates(notes: List<Note>): SortedMap<String, Int>{
        val notesToFilter: List<Note> = notes
        val notesMap: HashMap<String, Int> = HashMap()

        for(note in notesToFilter){
            val newDate = note.date.toString().split(" ")

            val key = newDate[1] + " " + newDate[2] + " " + newDate[5]
            when (val count = notesMap[key]) {
                null -> notesMap[key] = 1
                else -> notesMap[key] = count + 1
            }
        }

        return notesMap.toSortedMap()
    }
}