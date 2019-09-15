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
import com.example.taipeizoo.model.Plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlantListView extends RecyclerView {
    public interface PlantListViewListener {
        void onPlantClicked(Plant plant);
    }

    private List<Plant> plantList = new ArrayList<>();
    private PlantListViewListener plantListViewListener;
    private PlantAdapter adapter = new PlantAdapter();
    private RequestManager requestManager;

    public PlantListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlantListView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setplantListViewListener(PlantListViewListener plantListViewListener) {
        this.plantListViewListener = plantListViewListener;
    }

    public void setplantList(List<Plant> plantList) {
        if (plantList == null) return;

        this.plantList.clear();
        this.plantList.addAll(plantList);
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
                .error(R.drawable.botany);
        requestManager = Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    private class PlantAdapter extends RecyclerView.Adapter<ViewHolderPlant> implements
            ListPreloader.PreloadModelProvider<String> {

        private View.OnClickListener onClickListener = view -> {
            int position = (Integer) view.getTag();
            if (plantListViewListener != null) {
                plantListViewListener.onPlantClicked(plantList.get(position));
            }
        };

        @Override
        public int getItemCount() {
            return plantList.size();
        }

        @Override
        public ViewHolderPlant onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
            view.setOnClickListener(onClickListener);
            return new ViewHolderPlant(view, adapter);
        }

        @Override
        public void onBindViewHolder(ViewHolderPlant viewHolder, int position) {
            viewHolder.bind(position, plantList.get(position));
        }

        @NonNull
        @Override
        public List<String> getPreloadItems(int position) {
            String url = plantList.get(position).F_Pic01_URL;
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

    static class ViewHolderPlant extends RecyclerView.ViewHolder {

        protected View view;
        protected PlantAdapter adapter;
        @BindView(R.id.image)
        protected ImageView image;
        @BindView(R.id.title)
        protected TextView name;
        @BindView(R.id.description)
        protected TextView description;

        public ViewHolderPlant(View view, PlantAdapter adapter) {
            super(view);

            this.view = view;
            this.adapter = adapter;
            ButterKnife.bind(this, this.view);
        }

        public void bind(int position, Plant plant) {
            adapter.getRequestManager()
                    .load(plant.F_Pic01_URL)
                    .into(image);
            name.setText(plant.F_Name_Ch);
            description.setText(plant.F_Feature);
            view.setTag(position);
        }

    }
}
