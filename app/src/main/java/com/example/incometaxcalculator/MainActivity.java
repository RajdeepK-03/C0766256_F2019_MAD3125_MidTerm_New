package com.example.incometaxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity  extends AppCompatActivity  {

    final Calendar calendar = Calendar.getInstance();
    TextView txtDate;
    RadioGroup rgGender;
    RadioButton rbMale;
    RadioButton rbFemale;
    RadioButton rbOthers;
    TextView txtFullName;
    EditText edtFname;
    EditText edtLname;
    Button btnSubmit;
    TextView txtAge;
    DatePickerDialog datePickerDialog;

    int dDay;
    int dMonth;
    int dYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}
