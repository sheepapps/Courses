package fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.catrunner.R;
import com.example.user.catrunner.databinding.HomeFragmentBinding;

import viewModels.HomeAndMapSharedViewModel;

public class HomeFragment extends Fragment {

    public FragmentTransaction fragmentTransaction;
    public ImageButton btnStart;
    public HomeFragment homeFragment = this;
    private HomeFragmentBinding binding;
    private HomeAndMapSharedViewModel homeAndMapSharedViewModel;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //homeAndMapSharedViewModel = ViewModelProviders.of(this.getActivity()).get(HomeAndMapSharedViewModel.class);
    }

    public void setHomeAndMapSharedViewModel(HomeAndMapSharedViewModel homeAndMapSharedViewModel) {
        this.homeAndMapSharedViewModel = homeAndMapSharedViewModel;
    }

    public HomeAndMapSharedViewModel getHomeAndMapSharedViewModel() {
        return homeAndMapSharedViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        binding.setViewModel(homeAndMapSharedViewModel);
        return binding.getRoot();
    }

    private void init()
    {

    }

}
