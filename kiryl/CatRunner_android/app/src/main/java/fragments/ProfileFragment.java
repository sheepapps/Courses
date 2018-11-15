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

import com.example.user.catrunner.databinding.ProfileFragmentBinding;
import com.example.user.catrunner.R;

import java.util.GregorianCalendar;

import viewModels.ProfileFragmentViewModel;

public class ProfileFragment extends Fragment {

    private ProfileFragmentViewModel profileFragmentViewModel;
    public RadioButton rbtnMale;
    public RadioButton rbtnFemale;
    public RadioButton rbtnOther;
    private ProfileFragmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.profile_fragment, container, false);
        profileFragmentViewModel = ViewModelProviders.of(this.getActivity()).get(ProfileFragmentViewModel.class);
        binding.setViewModel(profileFragmentViewModel);
        profileFragmentViewModel.getGender().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                onChangeGender();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        onChangeGender();
    }

    private void onChangeGender() {
        int curGender = profileFragmentViewModel.getGender().getValue();
        switch (curGender) {
            case 1:
                rbtnMale = getView().findViewById(R.id.rbtn_male);
                rbtnMale.setChecked(true);
                break;
            case 2:
                rbtnFemale = getView().findViewById(R.id.rbtn_female);
                rbtnFemale.setChecked(true);
                break;
            case 3:
                rbtnOther = getView().findViewById(R.id.rbtn_other);
                rbtnOther.setChecked(true);
                break;
        }
    }
}
