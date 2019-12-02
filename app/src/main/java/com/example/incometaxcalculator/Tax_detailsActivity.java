package com.example.incometaxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class Tax_detailsActivity extends AppCompatActivity {
    CRACustomer customer;
    TextView txtsin;
    TextView txtfull_Name;
    TextView txtgenDer;
    TextView txtgross_income,
            txttaxDate, txtfederal_Tax, txtprovincial_Tax, lblcpp,
            lblEmp_Insurance, lblRRSPcontributed, lblCfRRSP,
            lblTaxableIncome, lblTaxPaid;
    double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, federalTax,
            provincialTax, totalTaxPaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_details);
        txtsin = findViewById(R.id.txtSIN);
        txtfull_Name = findViewById(R.id.txtfull_Name);
        txtgenDer =   findViewById(R.id.txtGender);
        txtgross_income = findViewById(R.id.txtgrossIncome);
        lblRRSPcontributed = findViewById(R.id.txt_D_RRSPContri);
        lblcpp = findViewById(R.id.txtCpp);
        lblEmp_Insurance = findViewById(R.id.txt_D_empInsurance);
        lblCfRRSP = findViewById(R.id.txt_D_cfRRSP);
        lblTaxableIncome = findViewById(R.id.txt_D_taxableIncome);
        txtfederal_Tax = findViewById(R.id.txt_D_federalTax);
        txtprovincial_Tax = findViewById(R.id.txt_D_provincialTax);
        lblTaxPaid = findViewById(R.id.txt_D_taxPayed);


        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");

        txtsin.setText(" SIN: \t" + customer.getSinNumber());
        txtfull_Name.setText(" FULL NAME: \t" + customer.getFullName());
        txtgenDer.setText(" GENDER: \t" + customer.getGender());
        txtgross_income.setText(" GROSS INCOME: \t" + customer.getGrossIncome());
        lblRRSPcontributed.setText("RRSP Contributed: \t" + customer.getRrspContri());

        // calculate  cpp
        double grossIncome = customer.getGrossIncome();
        if(grossIncome > 57400.00){
            cpp = (57400.00 * 0.051); //5.10%
        } else {
            cpp = (grossIncome * 0.051);
        }
        lblcpp.setText("CPP COntribution in Year:\t" + cpp);
        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        lblEmp_Insurance.setText("Employeement Insurance: \t" + ei);
        // calculate RRSP
        rrsp = customer.getRrspContri();
        double maxRRSP = (grossIncome * 0.18); //18%
        if(rrsp > maxRRSP ){
            rrspCf = rrsp - maxRRSP;
            rrsp = maxRRSP;
        }else{
            rrsp = rrsp;
        }
        lblCfRRSP.setText("RRSP Carry forward: \t"+ rrspCf);
        //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);

        lblTaxableIncome.setText("Taxable income:\t" + (double) taxableIncome);
        //federal tax
        double calFederal = calcFedralTax();
        txtfederal_Tax.setText("Federal Tax: \t" + calFederal);
        // Provincial Tax
        double calProvincial = calcProvincialTax();
        txtprovincial_Tax.setText("Provincial Tax:\t" + calProvincial);
        // total tax paid
        double taxpaid = calTaxPaid();
        lblTaxPaid.setText("Total tax Paid:\t" + taxpaid);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    