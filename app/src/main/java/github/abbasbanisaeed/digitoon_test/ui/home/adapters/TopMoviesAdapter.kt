package github.abbasbanisaeed.digitoon_test.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import github.abbasbanisaeed.digitoon_test.databinding.ItemHomeMoviesTopBinding
import github.abbasbanisaeed.digitoon_test.model.ResponseMoviesTop
import github.abbasbanisaeed.digitoon_test.model.ResponseMoviesTop.*
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor(): RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomeMoviesTopBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesAdapter.ViewHolder {
        binding = ItemHomeMoviesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()

    }

    override fun onBindViewHolder(holder: TopMoviesAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount()= if (differ.currentList.size > 5) 5 else differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: Search) {
            binding.apply {
                movieNameTxt.text = item.title
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

    private val differCallback = object : DiffUtil.ItemCallback<ResponseMoviesTop.Search>() {
        override fun areItemsTheSame(oldItem: ResponseMoviesTop.Search, newItem: ResponseMoviesTop.Search): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: ResponseMoviesTop.Search, newItem: ResponseMoviesTop.Search): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
