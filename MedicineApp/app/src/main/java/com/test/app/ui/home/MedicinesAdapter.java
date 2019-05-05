package com.test.app.ui.home;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.test.app.R;
import com.test.app.data.model.Medicine;
import com.test.app.databinding.ItemMedicineBinding;
import com.test.app.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MedicinesAdapter extends RecyclerView.Adapter<BaseViewHolder> implements Filterable {
    private List<Medicine> medicineList = new ArrayList<>();
    private List<Medicine> medicineListFiltered = new ArrayList<>();
    private ItemClickListener listener;

    MedicinesAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    public void clearItems() {
        medicineList.clear();
        medicineListFiltered.clear();
    }

    public void addItems(List<Medicine> medicines) {
        this.medicineList.addAll(medicines);
        this.medicineListFiltered.addAll(medicines);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMedicineBinding binding = ItemMedicineBinding
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
        return medicineListFiltered.size();
    }

    class MyViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemMedicineBinding mBinding;

        MyViewHolder(ItemMedicineBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.getRoot().setOnClickListener(this);
            mBinding.tvAdd.setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            Medicine medicine = medicineListFiltered.get(position);
            mBinding.setMedicine(medicine);
            mBinding.executePendingBindings();

            if (medicine.price.equalsIgnoreCase(medicine.sellingprice)) {
                mBinding.tvPrice.setPaintFlags(mBinding.tvPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            } else {
                mBinding.tvPrice.setPaintFlags(mBinding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                if (v.getId() == R.id.tvAdd) {
                    listener.onAddClick(medicineListFiltered.get(getAdapterPosition()));
                } else {
                    listener.onItemClick(medicineListFiltered.get(getAdapterPosition()));
                }
            }
        }
    }

    public interface ItemClickListener {
        void onAddClick(Medicine medicine);

        void onItemClick(Medicine medicine);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    medicineListFiltered.clear();
                    medicineListFiltered.addAll(medicineList);
                } else {
                    List<Medicine> filteredList = new ArrayList<>();
                    for (Medicine row : medicineList) {
                        if (row.brandname.toLowerCase().contains(charString)
                                || row.genericname.toLowerCase().contains(charString)) {
                            filteredList.add(row);
                        }
                    }
                    medicineListFiltered.clear();
                    medicineListFiltered.addAll(filteredList);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = medicineListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                medicineListFiltered = (ArrayList<Medicine>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

