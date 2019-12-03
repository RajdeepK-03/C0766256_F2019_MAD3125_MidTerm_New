package com.example.incometaxcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity  extends AppCompatActivity  {
    CRACustomer customer;
    final Calendar calendar = Calendar.getInstance();
    private TextView txtDate;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale; 
    private RadioButton rbOthers;
    private TextView txtFullName;
    private EditText edtFirstname;
    private EditText edtLastname;
    private EditText edtSinNumber;
    private Button btnCal;
    private TextView txtAge;
    private EditText edtGross_Income;
    private EditText edtRRSP_Contributed;
    DatePickerDialog datePickerDialog;
    private String Gender_selected = "";
    int dDay;
    int dMonth;
    int dYear;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDate= findViewById(R.id.txtDate);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateFormat();
            }
        };

        txtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txtAge=findViewById(R.id.txtAge);
        rgGender=findViewById(R.id.rgGender);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        rbOthers=findViewById(R.id.rbOthers);
        edtSinNumber = findViewById(R.id.edtSinNum);
        edtFirstname = findViewById(R.id.edtFname);
        edtLastname = findViewById(R.id.edtLname);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbOthers = findViewById(R.id.rbOthers);
        edtGross_Income = findViewById(R.id.edtGrossIncome);
        btnCal=findViewById(R.id.btnCalculate);
        edtRRSP_Contributed = findViewById(R.id.edtRRSP);
        txtDate = findViewById(R.id.txtDate);


        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbMale){
                    Gender_selected = rbMale.getText().toString();
                }else if(checkedId == R.id.rbFemale){
                    Gender_selected  = rbFemale.getText().toString();
                }else {
                    Gender_selected = rbOthers.getText().toString();
                }
            }

        });

        //submit button

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double grossIncome = Double.parseDouble(edtGross_Income.getText().toString());
                Double rrsp = Double.parseDouble(edtRRSP_Contributed.getText().toString());
                customer = new CRACustomer(edtSinNumber.getText().toString(),
                        edtFirstname.getText().toString(),
                        edtLastname.getText().toString(),
                        Gender_selected, grossIncome, rrsp);
                Intent mIntent = new Intent(MainActivity.this, Tax_detailsActivity.class);
                mIntent.putExtra("CRACustomer", customer);
                startActivity(mIntent);
            }
        });
    };
    private String dateFormat() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDate.setText(" D.O.B\t:" + sdf.format(calendar.getTime()));

        LocalDate l = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = LocalDate.now(); //gets localDate
        Period diff = Period.between(l, now); //difference between the dates is calculated
        System.out.println(diff.getYears() + "years" + diff.getMonths() + "months" + diff.getDays() + "days");

        String n1=String.valueOf(diff.getYears());
        String n2=String.valueOf(diff.getMonths());
        String n3=String.valueOf(diff.getDays());
        String age="Age: "+n1+"Years"+n2+"Months"+n3+"Days";


        txtAge.setText(age);
        return  n1;



    }

}