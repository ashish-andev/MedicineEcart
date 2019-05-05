package com.test.app.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.test.app.BR;
import com.test.app.R;
import com.test.app.data.model.Medicine;
import com.test.app.databinding.ActivityDashboardBinding;
import com.test.app.ui.base.BaseBindingActivity;
import com.test.app.ui.cart.CartActivity;
import com.test.app.ui.medine_detail.MedicineDetailActivity;


public class HomeActivity extends BaseBindingActivity<ActivityDashboardBinding,
        HomeViewModel> implements HomeNavigator, MedicinesAdapter.ItemClickListener {

    private HomeViewModel mViewModel;
    private ActivityDashboardBinding mBinding;
    private MedicinesAdapter medicinesAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    public HomeViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mViewModel = new HomeViewModel();
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        setSupportActionBar(mBinding.toolbar);
        setUpRecyclerView();
        mViewModel.getMedicines();
    }

    private void setUpRecyclerView() {
        medicinesAdapter = new MedicinesAdapter(this);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this, 2));
        mBinding.recyclerView.setAdapter(medicinesAdapter);
    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO handle error
    }

    @Override
    public void onAddClick(Medicine medicine) {
        mViewModel.addToCart(medicine);
    }

    @Override
    public void onItemClick(Medicine medicine) {
        Intent intent = new Intent(this, MedicineDetailActivity.class);
        intent.putExtra("medicine", medicine);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        final SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null && searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!searchView.isIconified()) {
                        searchView.setIconified(true);
                    }
                    searchItem.collapseActionView();
                    medicinesAdapter.getFilter().filter(query.trim());
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    medicinesAdapter.getFilter().filter(query.trim());
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_my_cart:
                startActivity(new Intent(this, CartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
