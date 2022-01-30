package com.latinos.mobiletest.features.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.latinos.mobiletest.R

@BindingAdapter("bindImageViewLocalDrawable")
fun bindImageViewLocalDrawable(imageView: ImageView, drawable: Int) {
    if (drawable != 0) {
        imageView.setImageResource(drawable)
    }
}

@BindingAdapter("bindImageBorderRounded", "bindDefaultDrawable")
fun setImageBorderRounded(imageView: ImageView, imgUrl: String?, drawable: Int) {
    if (imgUrl != null) {
        Glide.with(imageView.context)
            .load(imgUrl)
            .transform(CenterCrop(), RoundedCorners(16))
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}

@BindingAdapter("bindImageBorderRoundedNew")
fun bindImageBorderRoundedNew(imageView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {
        Glide.with(imageView.context)
            .load(imgUrl)
            .transform(CenterCrop(), RoundedCorners(16))
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}

@BindingAdapter("bindImageSimple")
fun bindImageSimple(imageView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {
        Glide.with(imageView.context)
            .load(imgUrl)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}

@BindingAdapter("bindImage", "bindDrawable")
fun setImage(imageView: ImageView, imgUrl: String?, drawable: Int) {
    if (imgUrl != null) {
        Glide.with(imageView.context)
            .load(imgUrl)
            .error(drawable)
            .placeholder(drawable)
            .into(imageView)
    }
}