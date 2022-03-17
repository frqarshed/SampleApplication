package com.sample.myapplication.ui.main.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sample.domain.model.Ad
import com.sample.myapplication.R
import com.sample.myapplication.databinding.ItemAdBinding


class OnClickListener(val clickListener: (ad: Ad, imageView: ImageView) -> Unit) {
    fun onClick(ad: Ad, imageView: ImageView) = clickListener(ad, imageView)
}

class AdsAdapter(
    private val context: Context,
    private val clickListener: OnClickListener
) : RecyclerView.Adapter<AdsAdapter.ViewHolder>() {

    private var ads = mutableListOf<Ad>()

    fun setAds(adsList: List<Ad>) {
        ads.clear()
        ads.addAll(adsList)
        notifyDataSetChanged()
    }

    fun getAdAtPosition(position: Int): Ad = ads[position]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ad = ads[position]
        holder.bind(ads[position])
    }

    override fun getItemCount(): Int {
        return ads.size
    }

    inner class ViewHolder(private val binding: ItemAdBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ad: Ad) {
            binding.imageView.transitionName = ad.uid
            binding.cardView.setOnClickListener {
                clickListener.onClick(ad, binding.imageView)
            }
            binding.title.text = ad.name
            binding.createdTime.text = ad.price

            val drawable = CircularProgressDrawable(context).apply {
                setColorSchemeColors(
                    R.color.purple_200,
                    R.color.purple_700,
                    R.color.purple_500
                )
                centerRadius = 30f
                strokeWidth = 5f
                start()
            }

            Glide
                .with(context)
                .load(ad.imageUrlsThumbnails[0])
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .placeholder(drawable)
                .into(binding.imageView)
        }
    }
}