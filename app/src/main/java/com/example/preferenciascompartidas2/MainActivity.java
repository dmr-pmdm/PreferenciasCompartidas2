package com.example.preferenciascompartidas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnGuardar;
    private RadioGroup rgColor;
    private RadioButton rbRojo, rbAzul;
    private TextView txtCambiante;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = findViewById(R.id.btnGuardar);
        rgColor = findViewById(R.id.rgColor);
        rbRojo = findViewById(R.id.rbRojo);
        rbAzul = findViewById(R.id.rbAzul);
        txtCambiante = findViewById(R.id.txtCambiante);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        final SharedPreferences.Editor editor = prefs.edit();

        color = prefs.getInt("color", getResources().getColor(R.color.negro));
        txtCambiante.setTextColor(color);

        if (color == getResources().getColor(R.color.rojo)) {
            rbRojo.setChecked(true);
        } else if (color == getResources().getColor(R.color.azul)){
            rbAzul.setChecked(true);
        }

        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbAzul.isChecked()) {
                    color = getResources().getColor(R.color.azul);
                } else if (rbRojo.isChecked()) {
                    color = getResources().getColor(R.color.rojo);
                }
                txtCambiante.setTextColor(color);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("color", color);
                editor.apply();
            }
        });
    }
}
