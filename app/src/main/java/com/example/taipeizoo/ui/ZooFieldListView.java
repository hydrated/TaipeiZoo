package com.example.taipeizoo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taipeizoo.R;
import com.example.taipeizoo.model.ZooField;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZooFieldListView extends RecyclerView {

    public interface ZooFieldListViewListener {
        void onZooFieldClicked(ZooField zooField);
    }

    private ZooFieldListViewListener zooFieldListViewListener;

    private ZooFieldAdapter adapter = new ZooFieldAdapter();

    public ZooFieldListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZooFieldListView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setZooFieldListViewListener(ZooFieldListViewListener zooFieldListViewListener) {
        this.zooFieldListViewListener = zooFieldListViewListener;
    }

    private void init() {
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setLayoutManager(layoutManager);
        setAdapter(adapter);
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    private class ZooFieldAdapter extends Adapter<ViewHolderZooField> {

        private OnClickListener onClickListener = view -> {
            int position = (Integer) view.getTag();

        };

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public ViewHolderZooField onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zoo_field, parent, false);
            view.setOnClickListener(onClickListener);
            return new ViewHolderZooField(view, adapter);
        }

        @Override
        public void onBindViewHolder(ViewHolderZooField viewHolder, int position) {
            viewHolder.bind(position);
        }


    }

    static class ViewHolderZooField extends ViewHolder {


        protected View view;
        @BindView(R.id.image)
        protected ImageView image;
        @BindView(R.id.title)
        protected TextView name;


        public ViewHolderZooField(View view, ZooFieldAdapter adapter) {
            super(view);

            this.view = view;
            ButterKnife.bind(this, this.view);
        }

        public void bind(int position) {
            view.setTag(position);
        }


    }
}