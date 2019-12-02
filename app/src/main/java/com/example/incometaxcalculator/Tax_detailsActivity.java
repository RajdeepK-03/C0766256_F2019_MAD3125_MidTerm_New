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
    TextView txtgross_income;
    TextView txtfederal_Tax;
    TextView lblcpp;
    TextView txtprovincial_Tax;
    TextView txttaxDate;
    TextView lblEmp_Insurance;
    TextView lblRRSPcontributed;
    TextView lblCfRRSP;
    TextView lblTaxableIncome;
    TextView lblTaxPaid;
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
            Toast.makeText(this,"you mave have to face a penalty,amount exceeding",Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public double calcCpp(){
        // calculate  cpp
        if(customer.getGrossIncome() > 57400.00){
            cpp = (57400.00 * 0.051);
        } else {
            cpp = (customer.getGrossIncome() * 0.051);
        }
        return cpp;
    }
    public double calcFedralTax(){
        //calculate federal tax
        double temp = taxableIncome ;
        if(temp <= 12069.00){
            federalTax = 0;//0%
            temp = taxableIncome - 12069.00;
        }
        if(temp >= 12069.01){
            federalTax = (temp * 0.15);//15%
            temp = temp - 35561;
        }
        if(temp >= 47630.01){
            federalTax = (temp * 0.205); //20.50%
            temp = temp - 47628.99;
        }
        if(temp >= 95259.01){
            federalTax = (temp * 0.26); //26%
            temp = temp - 52407.99;
        }
        if (temp >= 147667.01){
            federalTax = (temp * 0.29);//29%
            temp = temp - 62703.99;
        }
        if(temp >= 210371.01){
            federalTax = (temp * 0.33);//33%
            //temp = temp - federalTax;
        }
        return federalTax;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
        double temp = taxableIncome ;

        if(temp <= 10582.00){
            provincialTax = 0;
            temp = taxableIncome - 10582.00;
        }
        if(temp >= 10582.01){
            provincialTax = (temp * 0.0505); //5.05%
            temp = temp - 33323.99;
        }
        if(temp >= 43906.01){
            provincialTax = (temp * 0.0915); //9.15%
            temp = temp - 43906.99;
        }
        if(temp >= 87813.01){
            provincialTax = (temp * 0.1116); //11.16%
            temp = temp - 62187.99;
        }
        if (temp >= 150000.01){
            provincialTax = (temp * 0.1216);//12.16%
            temp = temp - 69999.99;
        }
        if(temp >= 220000.01){
            provincialTax = (temp * 0.1316);//13.16%

        }
        return provincialTax;
    }
    public  double calTaxPaid(){
        return totalTaxPaid = federalTax + provincialTax;
    }

}