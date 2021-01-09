package pl.futuredev.capstoneproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.adapter_city_result.view.*
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.data.remote.entities.Image
import pl.futuredev.capstoneproject.data.remote.entities.Result
import javax.inject.Inject

class CitiesAdapter @Inject constructor(
    private val glide: RequestManager,
    var cities: List<Result>
) :
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    inner class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        return CitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_city_result,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val city = cities[position]
        val images: List<Image> = cities[position].images

        holder.itemView.apply {
            tv_city_name.text = city.name
            tv_city_snippet.text = city.snippet
            tv_country_id.text = city.countryId
            if (images.isNotEmpty()) {
                glide.load(city.images[0].sizes.original.url).into(iv_city)
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