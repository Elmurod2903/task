package uz.elmurod.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.elmurod.core.base.BaseFragment
import uz.elmurod.main.databinding.FragmentMainBinding
import uz.elmurod.main.fragments.category.*

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun setup() {
        super.setup()

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        val tabs = arrayOf(
            "Business",
            "Entertainment",
            "General",
            "Health",
            "Science",
            "Sports",
            "Technology"
        )
        val viewPagerAdapter = ViewPagerAdapter(
            fragmentActivity = childFragmentManager,
            lifecycle = viewLifecycleOwner.lifecycle
        )
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }


    class ViewPagerAdapter(fragmentActivity: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentActivity, lifecycle) {

        override fun getItemCount(): Int {
            return 7
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BusinessFragment()
                1 -> EntertainmentFragment()
                2 -> GeneralFragment()
                3 -> HealthFragment()
                4 -> ScienceFragment()
                5 -> SportsFragment()
                else -> TechnologyFragment()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }


}
