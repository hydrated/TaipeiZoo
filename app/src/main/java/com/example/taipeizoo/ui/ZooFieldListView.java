package com.example.taipeizoo.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.taipeizoo.R;
import com.example.taipeizoo.model.ZooField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZooFieldListView extends RecyclerView {

    public interface ZooFieldListViewListener {
        void onZooFieldClicked(ZooField zooField);
    }

    private List<ZooField> zooFieldList = new ArrayList<>();
    private ZooFieldListViewListener zooFieldListViewListener;
    private ZooFieldAdapter adapter = new ZooFieldAdapter();
    private RequestManager requestManager;

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

    public void setZooFieldList(List<ZooField> zooFieldList) {
        if (zooFieldList == null) return;

        this.zooFieldList.clear();
        this.zooFieldList.addAll(zooFieldList);
        refresh();
    }

    private void init() {
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setLayoutManager(layoutManager);
        setAdapter(adapter);
        ViewPreloadSizeProvider<String> viewPreloader = new ViewPreloadSizeProvider<>();
        RecyclerViewPreloader<String> preloader = new RecyclerViewPreloader<>(
                Glide.with(this),
                adapter,
                viewPreloader,
                30);
        this.addOnScrollListener(preloader);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);
        requestManager = Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    private class ZooFieldAdapter extends Adapter<ViewHolderZooField> implements
            ListPreloader.PreloadModelProvider<String> {

        private OnClickListener onClickListener = view -> {
            int position = (Integer) view.getTag();
            if (zooFieldListViewListener != null) {
                zooFieldListViewListener.onZooFieldClicked(zooFieldList.get(position));
            }
        };

        @Override
        public int getItemCount() {
            return zooFieldList.size();
        }

        @Override
        public ViewHolderZooField onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zoo_field, parent, false);
            view.setOnClickListener(onClickListener);
            return new ViewHolderZooField(view, adapter);
        }

        @Override
        public void onBindViewHolder(ViewHolderZooField viewHolder, int position) {
            viewHolder.bind(position, zooFieldList.get(position));
        }

        @NonNull
        @Override
        public List<String> getPreloadItems(int position) {
            String url = zooFieldList.get(position).E_Pic_URL;
            if (TextUtils.isEmpty(url)) {
                return Collections.emptyList();
            }
            return Collections.singletonList(url);
        }

        @Nullable
        @Override
        public RequestBuilder<?> getPreloadRequestBuilder(@NonNull String item) {
            return requestManager.load(item);
        }

        public RequestManager getRequestManager() {
            return requestManager;
        }
    }

    static class ViewHolderZooField extends ViewHolder {

        protected View view;
        protected ZooFieldAdapter adapter;
        @BindView(R.id.image)
        protected ImageView image;
        @BindView(R.id.title)
        protected TextView name;

        public ViewHolderZooField(View view, ZooFieldAdapter adapter) {
            super(view);

            this.view = view;
            this.adapter = adapter;
            ButterKnife.bind(this, this.view);
        }

        public void bind(int position, ZooField zooField) {
            adapter.getRequestManager()
                    .load(zooField.E_Pic_URL)
                    .into(image);
            view.setTag(position);
        }

    }

}