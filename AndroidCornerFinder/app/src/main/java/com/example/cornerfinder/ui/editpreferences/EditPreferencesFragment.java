package com.example.cornerfinder.ui.editpreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cornerfinder.databinding.FragmentEditPreferencesBinding;

public class EditPreferencesFragment extends Fragment {

    private FragmentEditPreferencesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditPreferencesViewModel editPreferencesViewModel =
                new ViewModelProvider(this).get(EditPreferencesViewModel.class);

        binding = FragmentEditPreferencesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEditPreferences;
        editPreferencesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
