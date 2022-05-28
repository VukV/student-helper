package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.databinding.FragmentMainBinding
import rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.viewpager.MainPagerAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
        initNavigation()
    }

    private fun initViewPager(){
        binding.viewPager.adapter = MainPagerAdapter(childFragmentManager)
    }


    private fun initNavigation(){
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_schedule -> {
                    binding.viewPager.setCurrentItem(MainPagerAdapter.SCHEDULE_FRAGMENT, false)
                }
                R.id.menu_notes -> {
                    binding.viewPager.setCurrentItem(MainPagerAdapter.NOTES_FRAGMENT, false)
                }
                R.id.menu_stats -> {
                    binding.viewPager.setCurrentItem(MainPagerAdapter.STATS_FRAGMENT, false)
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}