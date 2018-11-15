package viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.user.catrunner.R;

public class SettingsFragmentViewModel extends ViewModel {

    private MutableLiveData<Boolean> mCurrentSounds;
    private MutableLiveData<Boolean> mCurrentUnitKm;

    public void setUnit(View view) {
        boolean curKm;
        if (view.getId() == R.id.rbtn_km) {
            curKm = true;
        } else {
            curKm = false;
        }
        mCurrentUnitKm.setValue(curKm);
    }

    public MutableLiveData<Boolean> getUnit() {
        if (mCurrentUnitKm == null) {
            mCurrentUnitKm = new MutableLiveData<>();
            mCurrentUnitKm.setValue(true);
        }
        return mCurrentUnitKm;
    }

    public void setState(View view) {
        boolean state;
        if (view.getId() == R.id.rbtn_on) {
            state = true;
        } else {
            state = false;
        }
        mCurrentSounds.setValue(state);
    }

    public MutableLiveData<Boolean> getState() {
        if (mCurrentSounds == null) {
            mCurrentSounds = new MutableLiveData<Boolean>();
            mCurrentSounds.setValue(false);
        }
        return mCurrentSounds;
    }
}
