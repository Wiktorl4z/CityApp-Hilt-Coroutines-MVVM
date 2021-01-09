package pl.futuredev.capstoneproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.adapter_top_places.view.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.data.remote.entities.Image
import pl.futuredev.capstoneproject.data.remote.entities.Result
import javax.inject.Inject

class TopPlacesAdapter @Inject constructor(
    private val glide: RequestManager,
    private var cities: List<Result>
) :
    RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder>() {

    inner class TopPlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPlacesViewHolder {
        return TopPlacesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_top_places,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopPlacesViewHolder, position: Int) {
        val city = cities[position]
        val images: List<Image> = cities[position].images

        holder.itemView.apply {
            tvName.text = city.name
            tvSnippet.text = city.snippet
            if (images.isNotEmpty()) {
                glide.load(city.images[0].sizes.original.url).into(ivImage)
                if (images[0].ownerUrl.isEmpty()) {
                    ivDetails.visibility = View.GONE
                } else {
                    glide.load(R.drawable.more_details).into(ivDetails)
                }
            }

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(position)
                }
            }
            val scores = cities[position].score.toInt().toString()
            tvScore.text = "Scores: $scores/10"
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

}