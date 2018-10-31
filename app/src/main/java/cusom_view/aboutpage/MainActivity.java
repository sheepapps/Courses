package cusom_view.aboutpage;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import cusom_view.aboutpage.blur_view.SupportRenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Хз как это из активити убрать
//        CustomView customView = findViewById(R.id.view);
//        customView.setupWith((ViewGroup) findViewById(R.id.root))
//                .setFrameClearDrawable(getWindow().getDecorView().getBackground())
//                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
//                .setBlurRadius(8f)
//                .setHasFixedTransformationMatrix(true);

    }
}