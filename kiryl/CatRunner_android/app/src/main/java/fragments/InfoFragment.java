package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.catrunner.R;

import static com.example.user.catrunner.LoginActivity.typefaceOpenSansBold;
import static com.example.user.catrunner.LoginActivity.typefaceOpenSansRegular;

public class InfoFragment extends Fragment {

    public TextView txtDescription, txtAppDescription, txtAuthors, txtAppAuthors, txtShare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, null);
        txtDescription = view.findViewById(R.id.title_description);
        txtDescription.setTypeface(typefaceOpenSansBold);
        txtAppDescription = view.findViewById(R.id.app_description);
        txtAppDescription.setTypeface(typefaceOpenSansRegular);
        txtAuthors = view.findViewById(R.id.title_authors);
        txtAuthors.setTypeface(typefaceOpenSansBold);
        txtAppAuthors = view.findViewById(R.id.app_authors);
        txtAppAuthors.setTypeface(typefaceOpenSansRegular);
        txtShare = view.findViewById(R.id.title_share_and_rate);
        txtShare.setTypeface(typefaceOpenSansBold);
        return view;
    }
}
