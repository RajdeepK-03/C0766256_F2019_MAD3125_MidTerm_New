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

    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    // calculating CPP amount
    public double cppAmount(){
        double cpp_slab=57400.00;
        double cpp_rate=5.10;
        double actual_cpp=0.0;
        if(grossIncome>=cpp_slab)
        {
            actual_cpp=(cpp_slab*cpp_rate)/100;
        }
        else {
            actual_cpp=(grossIncome*cpp_rate)/100;
        }
        return actual_cpp;
    }

    // calculating RRSP amount
    public double rrspAmount(){
        double rrsp_perc=18.00;
        double actual_rrsp=(grossIncome*rrsp_perc)/100;
        if(actual_rrsp>rrsp_contri) {
            System.out.println("RRSP amount exceeded ,You may have to face penalty");
        }
        return actual_rrsp;
    }

    // calculating EI amount
    public double eiAmount(){

        double ei_slab=53100.00;
        double ei_rate=1.62;
        double actual_ei=0.0;
        if(grossIncome>=ei_slab)
        {
            actual_ei=(ei_slab*ei_rate)/100;
        }
        else {
            actual_ei=(grossIncome*ei_rate)/100;
        }
        return actual_ei;
    }
    public void print(){
        System.out.println(total_taxable_amount);
    }

    // Calculating provincial tax
    public double provincialTax(){
        double pro_tax=0.0;

        double first_slab_perc=5.05;
        double first_slab=33324;

        double second_slab_perc=9.15;
        double second_slab=43907;

        double third_slab_perc=11.16;
        double third_slab=62187;

        double fourth_slab_perc=12.16;
        double fourth_slab=70000;

        double final_slab=0.01;
        double final_slab_perc=13.16;
        total_taxable_amount=total_taxable_amount-10582.00;
        if(total_taxable_amount<=first_slab) {
            pro_tax = (first_slab * first_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - first_slab;
        }

        if(total_taxable_amount<=second_slab) {
            pro_tax = (second_slab * second_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - second_slab;
        }
        if(total_taxable_amount<=third_slab) {
            pro_tax = (third_slab * third_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - third_slab;
        }
        if(total_taxable_amount<=fourth_slab) {
            pro_tax = (fourth_slab * fourth_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - fourth_slab;
        }
        if(total_taxable_amount<=final_slab) {
            pro_tax=(final_slab * final_slab_perc)/100;
        }
        return pro_tax;
    }

    // Calculating Federal tax
    public double federalTax(){
        double fed_tax=0.0;

        double first_slab_perc=15.00;
        double first_slab=35561;

        double second_slab_perc=20.50;
        double second_slab=47628.99;

        double third_slab_perc=26.00;
        double third_slab=52407.99;

        double fourth_slab_perc=29.00;
        double fourth_slab=60703.99;

        double final_slab=0.01;
        double final_slab_perc=33.00;
        total_taxable_amount=total_taxable_amount-12069.00;
        if(total_taxable_amount>=first_slab) {
            fed_tax = (first_slab * first_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - first_slab;
        }

        if(total_taxable_amount>=second_slab) {
            fed_tax = (second_slab * second_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - second_slab;
        }
        if(total_taxable_amount>=third_slab) {
            fed_tax = (third_slab * third_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - third_slab;
        }
        if(total_taxable_amount>=fourth_slab) {
            fed_tax = (fourth_slab * fourth_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - fourth_slab;
        }
        if(total_taxable_amount>=final_slab) {
            fed_tax=(final_slab * final_slab_perc)/100;
        }
        return fed_tax;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sin_number);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(full_name);
        dest.writeDouble(grossIncome);
        dest.writeDouble(rrsp_contri);
        dest.writeDouble(EI);
        dest.writeDouble(total_taxable_amount);
        dest.writeDouble(total_tax_paid);
    }

    // http://zetcode.com/java/numberformat/
    public String amountFomatter()
    {
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        String val = nf.format("$"+this);

        return val;

    }
}

