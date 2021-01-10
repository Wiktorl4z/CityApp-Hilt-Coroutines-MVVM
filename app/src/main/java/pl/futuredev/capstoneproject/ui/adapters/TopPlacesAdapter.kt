package pl.futuredev.capstoneproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.data.remote.entities.Image
import pl.futuredev.capstoneproject.data.remote.entities.Result
import pl.futuredev.capstoneproject.databinding.AdapterTopPlacesBinding
import javax.inject.Inject

class TopPlacesAdapter @Inject constructor(
    private val glide: RequestManager,
    private var cities: List<Result>
) :
    RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder>() {

    private var onItemClickListener: ((Int) -> Unit)? = null

    class TopPlacesViewHolder(private val itemBinding: AdapterTopPlacesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        val name = itemBinding.tvName
        val snippet = itemBinding.tvSnippet
        val image = itemBinding.ivImage
        val details = itemBinding.ivDetails
        val score = itemBinding.tvScore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPlacesViewHolder {
        val itemBinding =
            AdapterTopPlacesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPlacesViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: TopPlacesViewHolder, position: Int) {
        val city = cities[position]
        val images: List<Image> = cities[position].images

        holder.itemView.apply {
            holder.name.text = city.name
            holder.snippet.text = city.snippet
            if (images.isNotEmpty()) {
                glide.load(city.images[0].sizes.original.url).into(holder.image)
                if (images[0].ownerUrl.isEmpty()) {
                    holder.details.visibility = View.GONE
                } else {
                    glide.load(R.drawable.more_details).into(holder.details)
                }
            }

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(position)
                }
            }
            val scores = cities[position].score.toInt().toString()
            holder.score.text = "Scores: $scores/10"
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

}