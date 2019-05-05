package com.test.app.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.test.app.BR;
import com.test.app.R;
import com.test.app.data.model.Cart;
import com.test.app.databinding.ActivityCartBinding;
import com.test.app.ui.base.BaseBindingActivity;
import com.test.app.ui.home.HomeActivity;


public class CartActivity extends BaseBindingActivity<ActivityCartBinding,
        CartViewModel> implements CartNavigator, CartAdapter.ItemClickListener {

    private CartViewModel mViewModel;
    private ActivityCartBinding mBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    public CartViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mViewModel = new CartViewModel();
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        setSupportActionBar(mBinding.toolbar);
        showBackButton();
        setUpRecyclerView();
        mViewModel.getCartItems();
    }

    private void setUpRecyclerView() {
        CartAdapter medicinesAdapter = new CartAdapter(this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        mBinding.recyclerView.setAdapter(medicinesAdapter);
    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO handle error
    }

    @Override
    public void goToHomeScreen() {
        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onUpdateQuantity(Cart cart) {
        mViewModel.updateQuantity(cart);
    }

    @Override
    public void onRemove(Cart cart) {
        mViewModel.removeFromCart(cart);
    }
}
