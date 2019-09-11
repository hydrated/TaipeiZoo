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
import com.example.taipeizoo.R;
import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.ui.PlantListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZooFieldDetailFragment extends Fragment {

    public static ZooFieldDetailFragment getFragment() {
        return new ZooFieldDetailFragment();
    }

    @BindView(R.id.detail_image)
    ImageView image;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.detail_note)
    TextView note;
    @BindView(R.id.detail_url)
    TextView url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zoo_field_detail, container, false);

        if (view != null) ButterKnife.bind(this, view);

        return (view != null ? view : super.onCreateView(inflater, container, savedInstanceState));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setZooDetail(ZooField zooField) {
        Glide.with(this)
                .load(zooField.E_Pic_URL)
                .into(image);

        description.setText(zooField.E_Info);
        note.setText(zooField.E_Memo + '\n' + zooField.E_Category);
        url.setMovementMethod(LinkMovementMethod.getInstance());
        url.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(zooField.E_URL));
            startActivity(browserIntent);
        });
    }
}
