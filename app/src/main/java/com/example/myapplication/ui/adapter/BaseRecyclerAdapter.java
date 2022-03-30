package com.example.myapplication.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerAdapter<Bean, Binding extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_CONTENT = 1;
    public List<Bean> baseList;
    private OnCellClickListener<Binding, Bean> onCellClickListener;

    abstract int getLayoutRes();

    public interface OnCellClickListener<Binding, Bean> {
        void onCellClick(Binding binding, Bean bean);
    }

    public void setOnCellClickListener(OnCellClickListener<Binding, Bean> onCellClickListener) {
        this.onCellClickListener = onCellClickListener;
    }

    public BaseRecyclerAdapter() {
        super();
    }

    public BaseRecyclerAdapter(
            final List<Bean> baseList
    ) {
        super();
        this.baseList = baseList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContentViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        getLayoutRes(),
                        parent,
                        false
                )
        );
    }


    class ContentViewHolder extends RecyclerView.ViewHolder {
        Binding binding;

        public ContentViewHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return baseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE_CONTENT;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
        contentViewHolder.itemView.setOnClickListener(v -> {
            if (onCellClickListener != null) {
                onCellClickListener.onCellClick(contentViewHolder.binding, baseList.get(position));
                //notifyItemChanged(position);
            }
        });
        bindData(contentViewHolder.binding, position);
    }

    public abstract void bindData(Binding binding, int position);

    public void replaceData(List<Bean> newList) {
        baseList = newList;
        notifyDataSetChanged();
    }
}