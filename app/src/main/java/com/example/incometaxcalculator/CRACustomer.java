package com.example.incometaxcalculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.widget.EditText;

import java.util.Date;

public class CRACustomer implements Parcelable
{
    String sinNumber;
    String firstName;
    String lastName;
    String fullName;
    String gender;
    Date birthDate;
    Date filingDate;

    int age;
    double grossIncome, federalTax, provicialTax, empInsurance;
    double rrspContributed, rrspCarryForward, taxableIncome, taxPaid;

    public CRACustomer(String sinNumber, String firstName,
                       String lastName, String gender, double grossIncome, double rrspContri)
    {
        this.sinNumber = sinNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.gender = gender;
        this.grossIncome = grossIncome;
        this.rrspContributed = rrspContri;
    }


    public String getSinNumber() {
        return sinNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName()
    {
        return lastName.toUpperCase() + ", " +
                firstName.substring(0,1).toUpperCase() + firstName.substring(1);
    }
    public String getGender(){
        return  gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getEmpInsurance() {
        return empInsurance;
    }

    public double getRrspContri() {
        return rrspContributed;
    }

    public double getRrspCarryForward() {
        return rrspCarryForward;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getTaxPaid() {
        return taxPaid;
    }

    public double getFederalTax() {
        return federalTax;
    }

    public double getProvicialTax() {
        return provicialTax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sinNumber);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(fullName);
        dest.writeString(gender);
        dest.writeDouble(grossIncome);
        dest.writeDouble(rrspContributed);
//        dest.writeDouble(taxableIncome);
//        dest.writeDouble(federalTax);

    }
    public CRACustomer(Parcel parcel){
        sinNumber = parcel.readString();
        firstName = parcel.readString();
        lastName = parcel.readString();
        fullName = parcel.readString();
        gender = parcel.readString();
        grossIncome = parcel.readDouble();
        rrspContributed = parcel.readDouble();

    }
    public  static final Parcelable.Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel parcel) {
            return new CRACustomer(parcel);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };



}
