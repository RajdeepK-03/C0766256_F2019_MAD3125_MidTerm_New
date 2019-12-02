package com.example.incometaxcalculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class CRACustomer implements Parcelable {
    public static void main(String[] args) {

    }

    String sin_number;
    String first_name;
    String last_name;
    String full_name = last_name.toUpperCase() + "," + first_name;
    double grossIncome;
    double rrsp_contri;

    //setter and getter
    public String getSin_number() {
        return sin_number;
    }

    public void setSin_number(String sin_number) {
        this.sin_number = sin_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getRrsp_contri() {
        return rrsp_contri;
    }

    public void setRrsp_contri(double rrsp_contri) {
        this.rrsp_contri = rrsp_contri;
    }

    double EI;
    double total_taxable_amount = grossIncome - (cppAmount() + rrspAmount() + eiAmount());
    double total_tax_paid = provincialTax() + federalTax();

    protected CRACustomer(Parcel in) {
        sin_number = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        full_name = in.readString();
        grossIncome = in.readDouble();
        rrsp_contri = in.readDouble();
        EI = in.readDouble();
        total_taxable_amount = in.readDouble();
        total_tax_paid = in.readDouble();
    }

}
