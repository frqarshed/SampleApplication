package com.sample.myapplication.ui.main.details;

import static com.sample.data.core.Constants.DATE_PATTERN_DEFAULT_LOCAL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.sample.domain.model.Ad;
import com.sample.myapplication.R;
import com.sample.myapplication.databinding.DetailsFragmentBinding;
import com.sample.myapplication.ui.main.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class AdDetailsJavaFragment extends BaseFragment<DetailsFragmentBinding, AdDetailsViewModel> {
    @NonNull
    @Override
    public DetailsFragmentBinding setupViewBinding(@NonNull View view) {
        return DetailsFragmentBinding.bind(view);
    }

    @NonNull
    @Override
    public AdDetailsViewModel setupViewModel() {
        return new ViewModelProvider(this).get(AdDetailsViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.details_fragment;
    }

    @Override
    public void onViewCreated(@NonNull LayoutInflater inflater, @NonNull View view, @Nullable Bundle savedInstanceState) {
        AdDetailsJavaFragmentArgs args = AdDetailsJavaFragmentArgs.fromBundle(getArguments());
        Ad ad = args.getAd();
        viewBinding.title.setText(ad.getName());
        viewBinding.price.setText(ad.getPrice());

        String date = new SimpleDateFormat(DATE_PATTERN_DEFAULT_LOCAL, Locale.US).format(Objects.requireNonNull(ad.getCreatedAt()));
        viewBinding.createdTime.setText(date);
        setImage(ad);
    }

    private void setImage(Ad ad) {
        CircularProgressDrawable drawable = new CircularProgressDrawable(requireContext());
        drawable.setColorSchemeColors(R.color.purple_200,
                R.color.purple_700,
                R.color.purple_500);
        drawable.setCenterRadius(30f);
        drawable.setStrokeWidth(5f);
        drawable.start();

        Glide.with(requireContext())
                .load(ad.getImageUrlsThumbnails().get(0))
                .placeholder(drawable)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewBinding.imageView);
    }
}
