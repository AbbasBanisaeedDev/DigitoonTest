package github.abbasbanisaeed.digitoon_test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import github.abbasbanisaeed.digitoon_test.R
import github.abbasbanisaeed.digitoon_test.databinding.FragmentDetailBinding
import github.abbasbanisaeed.digitoon_test.databinding.FragmentHomeBinding
import github.abbasbanisaeed.digitoon_test.ui.home.adapters.LastMoviesAdapter
import github.abbasbanisaeed.digitoon_test.ui.home.adapters.TopMoviesAdapter
import github.abbasbanisaeed.digitoon_test.utils.initRecycler
import github.abbasbanisaeed.digitoon_test.utils.showInvisible
import github.abbasbanisaeed.digitoon_test.viewmodel.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    //Other
    private val viewModel: HomeViewModel by viewModels()
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Call api
        viewModel.loadTopMoviesList("batman")
        viewModel.lastTopMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {
            //Get top movies slider
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.search)
                topMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter
                )

                //Indicator
                pagerHelper.attachToRecyclerView(topMoviesRecycler)
                topMoviesIndicator.attachToRecyclerView(topMoviesRecycler, pagerHelper)

            }
            //Click
            topMoviesAdapter.setOnItemClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.imdbID)
                findNavController().navigate(direction)
            }

            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.search)
                lastMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext()),
                    lastMoviesAdapter
                )

            }
            //Click
            lastMoviesAdapter.setOnItemClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.imdbID)
                findNavController().navigate(direction)
            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInvisible(true)
                    moviesScrollLay.showInvisible(false)
                } else {
                    moviesLoading.showInvisible(false)
                    moviesScrollLay.showInvisible(true)
                }
            }

        }

    }
}