package pl.futuredev.capstoneproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.databinding.AdapterCityResultBinding
import javax.inject.Inject

class FavCitiesAdapter @Inject constructor(
    private val glide: RequestManager,
    var cities: List<City>
) :
    RecyclerView.Adapter<FavCitiesAdapter.FavCitiesViewHolder>() {

    class FavCitiesViewHolder(private val itemBinding: AdapterCityResultBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val name = itemBinding.tvCityName
        val snippet = itemBinding.tvCitySnippet
        val image = itemBinding.ivCity
        val countId = itemBinding.tvCountId
    }

    private var onItemClickListener: ((City) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCitiesViewHolder {
        val itemBinding =
            AdapterCityResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavCitiesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavCitiesViewHolder, position: Int) {
        val city = cities[position]
        holder.itemView.apply {
            holder.name.text = city.name
            holder.snippet.text = city.snippet
            holder.countId.text = city.cityId
            if (cities[position].image != null) {
                glide.load(cities[position].image?.get(0)?.sizes?.original?.url).into(holder.image)
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

    fun setOnItemClickListener(onItemClickListener: (City) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

}