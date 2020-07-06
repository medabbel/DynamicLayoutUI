package com.example.dynamiclayoutui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditText;
    Button showBtn;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams params;
    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        if (savedInstanceState != null) {
            names = savedInstanceState.getStringArrayList("names");
            if (names != null && names.size() > 0) {
                for (int i = 0; i < names.size(); i++) {
                    TextView textView = new TextView(this);
                    textView.setWidth(100);
                    textView.setTextSize(20);
                    textView.setText("Bienvenue " + names.get(i));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    textView.setLayoutParams(params);
                    linearLayout.addView(textView, 0);
                }

            }
        }
    }
    private void initViews() {
        nameEditText = findViewById(R.id.nameEditText);
        showBtn = findViewById(R.id.showBtn);
        showBtn.setOnClickListener(this);

        linearLayout = findViewById(R.id.dynLinearLayout);

    }
    @Override
    public void onClick(View view) {
        if (nameEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Veuillez saisissez un nom", Toast.LENGTH_SHORT).show();
            return;
        }
        names.add(nameEditText.getText().toString());
        TextView textView = new TextView(this);
        textView.setWidth(100);
        textView.setTextSize(20);

        textView.setText("Bienvenue " + names.get(names.size() - 1));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textView.setLayoutParams(params);
        linearLayout.addView(textView, 0);
        nameEditText.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("names", names);
    }


}
