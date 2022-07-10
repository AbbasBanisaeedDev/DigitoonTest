package github.abbasbanisaeed.digitoon_test.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import github.abbasbanisaeed.digitoon_test.databinding.ItemHomeMoviesLastBinding
import github.abbasbanisaeed.digitoon_test.model.ResponseMoviesTop
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor(): RecyclerView.Adapter<LastMoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomeMoviesLastBinding
    private var moviesList= emptyList<ResponseMoviesTop.Search>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMoviesAdapter.ViewHolder {
        binding = ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()

    }

    override fun onBindViewHolder(holder: LastMoviesAdapter.ViewHolder, position: Int) {
        holder.bindItems(moviesList[position])
        holder.setIsRecyclable(false)
    }
    override fun getItemCount()= moviesList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItems(item: ResponseMoviesTop.Search) {
            binding.apply {
                movieNameTxt.text = item.title
                movieYearTxt.text = item.year
                moviePosterImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((ResponseMoviesTop.Search) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseMoviesTop.Search) -> Unit) {
        onItemClickListener = listener
    }


    fun setData(data: List<ResponseMoviesTop.Search>) {
        val moviesDiffUtil = MoviesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<ResponseMoviesTop.Search>, private val newItem: List<ResponseMoviesTop.Search>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }

    }





