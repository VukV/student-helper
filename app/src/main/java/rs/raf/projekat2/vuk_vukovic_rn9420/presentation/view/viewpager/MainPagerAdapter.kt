package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.viewpager

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments.NotesFragment
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments.ScheduleFragment
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments.StatsFragment

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object {
        private const val FRAGMENT_COUNT = 3
        const val SCHEDULE_FRAGMENT = 0
        const val NOTES_FRAGMENT = 1
        const val STATS_FRAGMENT = 2
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            SCHEDULE_FRAGMENT -> ScheduleFragment()
            NOTES_FRAGMENT -> NotesFragment()
            else -> StatsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            SCHEDULE_FRAGMENT -> getTitleFromR(R.string.menu_schedule)
            NOTES_FRAGMENT -> getTitleFromR(R.string.menu_notes)
            else -> getTitleFromR(R.string.menu_stats)
        }
    }

    private fun getTitleFromR(id: Int): String{
        return Resources.getSystem().getString(id)
    }
}