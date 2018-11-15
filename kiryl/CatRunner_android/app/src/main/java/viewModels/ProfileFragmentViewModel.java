package viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.user.catrunner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ProfileFragmentViewModel extends ViewModel {

    private MutableLiveData<Integer> mCurrentGender;
    private MutableLiveData<String> mCurrentName;
    private MutableLiveData<GregorianCalendar> mCurrentDate;

    public void setDate(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy", Locale.US);
        Date date = new Date();
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
//
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        mCurrentDate.setValue(calendar);
    }

    public MutableLiveData<GregorianCalendar> getDate() {
        if (mCurrentDate == null) {
            mCurrentDate = new MutableLiveData<GregorianCalendar>();
            setDate("01.11.2018");
        }
        return mCurrentDate;
    }

    public void setName(String name) {
        mCurrentName.setValue(name);
    }

    public MutableLiveData<String> getName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
            mCurrentName.setValue("Kitty");
        }
        return mCurrentName;
    }

    public void setGender(View view) {
        int curFragment = 1;
        switch (view.getId()) {
            case R.id.rbtn_male:
                curFragment = 1;
                break;
            case R.id.rbtn_female:
                curFragment = 2;
                break;
            case R.id.rbtn_other:
                curFragment = 3;
                break;
        }
        mCurrentGender.setValue(curFragment);
    }

    public MutableLiveData<Integer> getGender() {
        if (mCurrentGender == null) {
            mCurrentGender = new MutableLiveData<Integer>();
            mCurrentGender.setValue(2);
        }
        return mCurrentGender;
    }
}
