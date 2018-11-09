package fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.user.catrunner.MapActivity;
import com.example.user.catrunner.R;

public class HomeFragment extends Fragment {

    public FragmentTransaction fragmentTransaction;
    public ImageButton btnStart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, null);
        btnStart = v.findViewById(R.id.btn_start);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.remove(HomeFragment.this);
                fragmentTransaction.commit();
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
