package cusom_view.aboutpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import cusom_view.aboutpage.blur_view.BlurView;

public class CustomView extends BlurView {

    // сюды засунуть объявление каждой вьюшки если надо что-то сней тут творить

    //например так
    private TextView mainWord;
    private TextView secondWord;


    CustomView(Context context) {
        super(context);
        init(context);
    }

    CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        View view = View.inflate(context, R.layout.custom_view, this);

        // тута все махинации с вьюшками если надо
        //secondWord = view.findViewById(R.id.secondWord);
        //secondWord.setText("Second");
    }
}
