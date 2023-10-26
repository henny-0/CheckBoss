package com.example.checkboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox java;
    private CheckBox android;
    private CheckBox cSharp;

    private TextView info;
    private RadioGroup payMethodGroup;
    private RadioButton payMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        java = findViewById(R.id.checkBoxJava);
        android = findViewById(R.id.checkBoxAndroid);
        cSharp = findViewById(R.id.checkBoxCSharp);
        info = findViewById(R.id.paymentinfo);
        payMethodGroup = findViewById(R.id.radioGroupPayment);



        View.OnClickListener checkListen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFees();
            }
        };

        java.setOnClickListener(checkListen);
        android.setOnClickListener(checkListen);
        cSharp.setOnClickListener(checkListen);

        payMethodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                calculateFees();
            }
        });


    }
    public void calculateFees(){
        int fees = 0;

        if(java.isChecked()) fees+= 30;
        if(android.isChecked()) fees+= 20;
        if(cSharp.isChecked()) fees+= 10;

        if(getPay().equals("")|| fees <= 0) {
            info.setText("");
        }else info.setText("Paying £" + fees + " through " + getPay() + subjectList());
    }

    public String getPay() {
        int selected = payMethodGroup.getCheckedRadioButtonId();
        payMethod = findViewById(selected);

        if (payMethod != null){
            return payMethod.getText().toString();
        }

        return "";
    }

    public String subjectList(){
        String list = "";
        if(java.isChecked()) list = list +"\n"+java.getText() ;
        if(android.isChecked()) list = list +"\n"+android.getText() ;
        if(cSharp.isChecked()) list = list +"\n"+cSharp.getText() ;

        return list;
    }
}

