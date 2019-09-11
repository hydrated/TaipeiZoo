package com.example.taipeizoo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taipeizoo.R;

import butterknife.ButterKnife;

public class ZooFieldDetailFragment extends Fragment {

    public static ZooFieldDetailFragment getFragment() {
        return new ZooFieldDetailFragment();
    }

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
}
