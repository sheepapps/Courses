package fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.catrunner.R;
import viewModels.HomeAndMapSharedViewModel;

public class MapFragment extends Fragment {

    private HomeAndMapSharedViewModel homeAndMapSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAndMapSharedViewModel = ViewModelProviders.of(this.getActivity()).get(HomeAndMapSharedViewModel.class);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_map, null);
    }
}
