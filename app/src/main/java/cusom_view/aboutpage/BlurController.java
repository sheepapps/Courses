package cusom_view.aboutpage;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

interface BlurController {
    float DEFAULT_SCALE_FACTOR = 8f;
    float DEFAULT_BLUR_RADIUS = 16f;

    void drawBlurredContent(Canvas canvas);

    void updateBlurViewSize();

    void onDrawEnd(Canvas canvas);

    void setBlurRadius(float radius);

    void setBlurAlgorithm(BlurAlgorithm alghorithm);

    void setFrameClearDrawable(@Nullable Drawable frameClearDrawable);

    void destroy();

    void setBlurEnabled(boolean enabled);

    void setBlurAutoUpdate(boolean enabled);

    void setHasFixedTransformationMatrix(boolean hasFixedTransformationMatrix);
}
