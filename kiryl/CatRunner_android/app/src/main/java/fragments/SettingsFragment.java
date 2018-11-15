package fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.user.catrunner.R;
import com.example.user.catrunner.databinding.SettingsFragmentBinding;

import viewModels.ProfileFragmentViewModel;
import viewModels.SettingsFragmentViewModel;

public class SettingsFragment extends Fragment {

    private SettingsFragmentViewModel settingsFragmentViewModel;
    private SettingsFragmentBinding binding;
    public RadioButton rbtnSoundOn;
    public RadioButton rbtnSounfOff;
    public RadioButton rbtnKm;
    public RadioButton rbtnMil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.settings_fragment, container, false);
        settingsFragmentViewModel = ViewModelProviders.of(this.getActivity()).get(SettingsFragmentViewModel.class);
        binding.setViewModel(settingsFragmentViewModel);
        settingsFragmentViewModel.getState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                onChangeStateSounds();
            }
        });
        settingsFragmentViewModel.getState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                onChangeUnit();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        onChangeStateSounds();
        onChangeUnit();
    }

    private void onChangeStateSounds() {
        boolean curState = settingsFragmentViewModel.getState().getValue();
        if (curState) {
            rbtnSoundOn = getView().findViewById(R.id.rbtn_on);
            rbtnSoundOn.setChecked(true);
        } else {
            rbtnSounfOff = getView().findViewById(R.id.rbtn_off);
            rbtnSounfOff.setChecked(true);
        }
    }

    private void onChangeUnit() {
        boolean curUnitKm = settingsFragmentViewModel.getUnit().getValue();
        if (curUnitKm == true) {
            rbtnKm = getView().findViewById(R.id.rbtn_km);
            rbtnKm.setChecked(true);
        } else {
            rbtnMil = getView().findViewById(R.id.rbtn_mil);
            rbtnMil.setChecked(true);
        }
    }
}
