package github.abbasbanisaeed.digitoon_test.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import github.abbasbanisaeed.digitoon_test.databinding.FragmentDetailBinding
import github.abbasbanisaeed.digitoon_test.utils.initRecycler
import github.abbasbanisaeed.digitoon_test.utils.showInvisible
import github.abbasbanisaeed.digitoon_test.viewmodel.DetailViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentDetailBinding

    //Other
    private var imdbID = ""
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get data
        imdbID = args.imdbID
        //Call api
       imdbID?.let{
            viewModel.loadDetailMovie(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //Load data
            viewModel.detailMovie.observe(viewLifecycleOwner) { response ->
                posterBigImg.load(response.poster)
                posterNormalImg.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                movieNameTxt.text = response.title
                movieRateTxt.text = response.imdbRating
                movieTimeTxt.text = response.runtime
                movieDateTxt.text = response.released
                movieSummaryInfo.text = response.plot
                movieActorsInfo.text = response.actors
            }
            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    detailLoading.showInvisible(true)
                    detailScrollView.showInvisible(false)
                } else {
                    detailLoading.showInvisible(false)
                    detailScrollView.showInvisible(true)
                }
            }

            //Back
            backImg.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }
}