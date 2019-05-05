package com.test.app.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test.app.data.model.Cart;
import com.test.app.data.model.Medicine;
import com.test.app.ui.cart.CartAdapter;
import com.test.app.ui.home.MedicinesAdapter;

import java.util.List;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("imageDrawable")
    public static void setImageDrawable(ImageView imageView, @DrawableRes int id) {
        Glide.with(imageView.getContext()).load(id).into(imageView);
    }

    @BindingAdapter("medicineAdapter")
    public static void medicineAdapter(RecyclerView recyclerView, List<Medicine> medicines) {
        MedicinesAdapter adapter = (MedicinesAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(medicines);
        }
    }

    @BindingAdapter("cartAdapter")
    public static void cartAdapter(RecyclerView recyclerView, List<Cart> cartItems) {
        CartAdapter adapter = (CartAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(cartItems);
        }
    }

}
