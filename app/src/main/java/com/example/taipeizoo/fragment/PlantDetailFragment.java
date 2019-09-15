package com.example.taipeizoo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.taipeizoo.R;
import com.example.taipeizoo.model.Plant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlantDetailFragment extends Fragment {

    public static PlantDetailFragment getFragment() {
        return new PlantDetailFragment();
    }

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.alias)
    TextView alias;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.recognition)
    TextView recognition;
    @BindView(R.id.functionality)
    TextView functionality;
    @BindView(R.id.last_update)
    TextView last_update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_detail, container, false);

        if (view != null) ButterKnife.bind(this, view);

        return (view != null ? view : super.onCreateView(inflater, container, savedInstanceState));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setPlant(Plant plant) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.botany)
                .error(R.drawable.botany);

        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(plant.F_Pic01_URL)
                .into(image);

        name.setText(plant.F_Name_Ch);
        description.setText(plant.F_Brief);
        alias.setText(plant.F_AlsoKnown);
        recognition.setText(plant.F_Feature);
        functionality.setText(plant.F_Function);
        last_update.setText("" + plant.F_Update);

    }
}
