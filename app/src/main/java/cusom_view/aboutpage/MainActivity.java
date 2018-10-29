package cusom_view.aboutpage;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.support.v8.renderscript.*;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BlurView blurView = (BlurView) findViewById(R.id.blurView);

        blurView.setupWith((ViewGroup) findViewById(R.id.root))
                .setFrameClearDrawable(getWindow().getDecorView().getBackground())
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(4f)
                .setHasFixedTransformationMatrix(true);
    }

}
