package pl.futuredev.capstoneproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import pl.futuredev.capstoneproject.data.remote.entities.Image
import pl.futuredev.capstoneproject.data.remote.entities.Result
import pl.futuredev.capstoneproject.databinding.AdapterCityResultBinding
import javax.inject.Inject

class CitiesAdapter @Inject constructor(
    private val glide: RequestManager,
    var cities: List<Result>
) :
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    class CitiesViewHolder(private val itemBinding: AdapterCityResultBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        val name = itemBinding.tvCityName
        val snippet = itemBinding.tvCitySnippet
        val image = itemBinding.ivCity
        val countId = itemBinding.tvCountId

    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val itemBinding =
            AdapterCityResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitiesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = cities[position]
        val images: List<Image> = cities[position].images

        holder.itemView.apply {
            holder.name.text = city.name
            holder.snippet.text = city.snippet
            holder.countId.text = city.countryId
            if (images.isNotEmpty()) {
                glide.load(city.images[0].sizes.original.url).into(holder.image)
            }

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(city)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setOnItemClickListener(onItemClickListener: (Result) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

}