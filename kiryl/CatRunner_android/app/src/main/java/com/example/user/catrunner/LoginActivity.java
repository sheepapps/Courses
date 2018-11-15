package com.example.user.catrunner;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private class User {
        private String name, password;

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }

    public static Typeface typefaceBebasNeue, typefaceOpenSansBold, typefaceOpenSansRegular;
    public Button btnLogin, btnCreateAccount;
    public android.support.design.widget.TextInputEditText edtPassword, edtLogin;
    public ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        typefaceBebasNeue = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue_Regular.otf");
        typefaceOpenSansBold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typefaceOpenSansRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");

        TextView helloText = findViewById(R.id.txt_hello);
        final SpannableStringBuilder text = new SpannableStringBuilder(getResources().getString(R.string.txt_hello));
        final ForegroundColorSpan style = new ForegroundColorSpan(getResources().getColor(R.color.colorAccentPink));
        text.setSpan(style, 5, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        helloText.setText(text);
        helloText.setTypeface(typefaceOpenSansBold);

        edtPassword = findViewById(R.id.password);
        edtLogin = findViewById(R.id.login);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setTypeface(typefaceOpenSansBold);
        btnCreateAccount = findViewById(R.id.btn_create_account);
        btnCreateAccount.setTypeface(typefaceOpenSansBold);

        users = new ArrayList<>();
        users.add(new User("cat", "runner"));
    }

    @Override
    protected void onStart() {
        super.onStart();
//================================================================
//      этот кусок кода потом придется заменять, т.к. данные будут приходить с сервера, а сейчас он просто для примера проверок
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(edtLogin.getText().toString(), edtPassword.getText().toString());
                boolean check = false;
                for (User i : users) {
                    if (i.password.equals(user.password) && i.name.equals(user.name)) {
                        check = true;
                    }
                }
                if (check) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Such user doesn't exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(edtLogin.getText().toString(), edtPassword.getText().toString());
                boolean check = false;
                for (User i : users) {
                    if (i.password.equals(user.password) && i.name.equals(user.name)) {
                        check = true;
                    }
                }
                if (check) {
                    Toast.makeText(LoginActivity.this, "Such user already exists", Toast.LENGTH_SHORT).show();
                } else {
                    users.add(user);
                    Toast.makeText(LoginActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                }
            }
        });
//================================================================
        TextWatcher txt = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtPassword.getText().length() != 0) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtPassword.addTextChangedListener(txt);
    }
}
