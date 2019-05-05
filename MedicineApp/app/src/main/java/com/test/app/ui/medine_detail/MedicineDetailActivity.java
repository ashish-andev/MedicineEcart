package com.test.app.ui.medine_detail;

import android.graphics.Paint;
import android.os.Bundle;

import com.test.app.BR;
import com.test.app.R;
import com.test.app.data.model.Medicine;
import com.test.app.databinding.ActivityMedicineDetailBinding;
import com.test.app.ui.base.BaseBindingActivity;


public class MedicineDetailActivity extends BaseBindingActivity<ActivityMedicineDetailBinding,
        MedicineDetailViewModel> implements MedicineDetailNavigator {

    private MedicineDetailViewModel mViewModel;
    private ActivityMedicineDetailBinding mBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_medicine_detail;
    }

    @Override
    public MedicineDetailViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mViewModel = new MedicineDetailViewModel();
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        setSupportActionBar(mBinding.toolbar);
        showBackButton();

        Medicine medicine = (Medicine) getIntent().getSerializableExtra("medicine");
        mViewModel.setMedicine(medicine);
        mBinding.setMedicine(medicine);

        if (medicine.price.equalsIgnoreCase(medicine.sellingprice)) {
            mBinding.tvPrice.setPaintFlags(mBinding.tvPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            mBinding.tvPrice.setPaintFlags(mBinding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        mBinding.executePendingBindings();
    }
}
