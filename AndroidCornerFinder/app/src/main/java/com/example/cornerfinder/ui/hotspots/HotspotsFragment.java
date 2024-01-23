package com.example.cornerfinder.ui.hotspots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cornerfinder.databinding.FragmentHotspotsBinding;

public class HotspotsFragment extends Fragment {

    private FragmentHotspotsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HotspotsViewModel hotspotsViewModel =
                new ViewModelProvider(this).get(HotspotsViewModel.class);

        binding = FragmentHotspotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        //final TextView textView = binding.textHotspots;
        //hotspotsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
