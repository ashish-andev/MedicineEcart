package com.test.app.ui.cart;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.app.MyApplication;
import com.test.app.R;
import com.test.app.data.model.Cart;
import com.test.app.databinding.ItemCartBinding;
import com.test.app.ui.base.BaseViewHolder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Cart> cartList = new ArrayList<>();
    private ItemClickListener listener;

    CartAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    public void clearItems() {
        cartList.clear();
    }

    public void addItems(List<Cart> cartItems) {
        this.cartList.addAll(cartItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class MyViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemCartBinding mBinding;

        MyViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.btnMinus.setOnClickListener(this);
            mBinding.btnPlus.setOnClickListener(this);
            mBinding.tvRemove.setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            Cart cart = cartList.get(position);
            mBinding.setCart(cart);

            int quantity = cart.quantity;
            DecimalFormat decimalFormat = new DecimalFormat("0.#");
            double price = Double.parseDouble(cart.price) * quantity;
            double sellingPrice = Double.parseDouble(cart.sellingprice) * quantity;
            mBinding.tvPrice.setText(MyApplication.getInstance().getString(R.string.rs,
                    decimalFormat.format(price)));
            if (price == sellingPrice) {
                mBinding.tvPrice.setPaintFlags(mBinding.tvPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            } else {
                mBinding.tvDisPrice.setText(MyApplication.getInstance().getString(R.string.rs,
                        decimalFormat.format(sellingPrice)));
                mBinding.tvPrice.setPaintFlags(mBinding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            Cart cart = cartList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.btnMinus:
                    if (cart.quantity > 1) {
                        cart.quantity -= 1;
                        cartList.set(getAdapterPosition(), cart);
                        notifyDataSetChanged();
                        if (listener != null) {
                            listener.onUpdateQuantity(cart);
                        }
                    } else {
                        cartList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        if (listener != null) {
                            listener.onRemove(cart);
                        }
                    }

                    break;
                case R.id.btnPlus:
                    cart.quantity += 1;
                    cartList.set(getAdapterPosition(), cart);
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.onUpdateQuantity(cart);
                    }
                    break;
                case R.id.tvRemove:
                    cartList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    if (listener != null) {
                        listener.onRemove(cart);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public interface ItemClickListener {
        void onUpdateQuantity(Cart cart);

        void onRemove(Cart cart);
    }

}

