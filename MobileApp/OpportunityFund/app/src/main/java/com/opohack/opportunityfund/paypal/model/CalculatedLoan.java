package com.opohack.opportunityfund.paypal.model;

import org.json.JSONObject;

/**
 * Created by vipulkanade on 5/13/15.
 */
public class CalculatedLoan {

    private int iDownPayment;
    private int iAmountFinanced;
    private int iAnnualPercentageRate;

    private String sFrequency;

    public int getDownPayment() {
        return iDownPayment;
    }

    public void setDownPayment(int iDownPayment) {
        this.iDownPayment = iDownPayment;
    }

    public int getAmountFinanced() {
        return iAmountFinanced;
    }

    public void setAmountFinanced(int iAmountFinanced) {
        this.iAmountFinanced = iAmountFinanced;
    }

    public int getAnnualPercentageRate() {
        return iAnnualPercentageRate;
    }

    public void setAnnualPercentageRate(int iAnnualPercentageRate) {
        this.iAnnualPercentageRate = iAnnualPercentageRate;
    }

    public String getFrequency() {
        return sFrequency;
    }

    public void setFrequency(String sFrequency) {
        this.sFrequency = sFrequency;
    }

    public double getPaymentFrequency() {
        return dPaymentFrequency;
    }

    public void setPaymentFrequency(double dPaymentFrequency) {
        this.dPaymentFrequency = dPaymentFrequency;
    }

    public double getTotalInterest() {
        return dTotalInterest;
    }

    public void setTotalInterest(double dTotalInterest) {
        this.dTotalInterest = dTotalInterest;
    }

    public double getTotalPayment() {
        return dTotalPayment;
    }

    public void setTotalPayment(double dTotalPayment) {
        this.dTotalPayment = dTotalPayment;
    }

    private double dPaymentFrequency;
    private double dTotalInterest;
    private double dTotalPayment;


    public CalculatedLoan() {
    }

    public CalculatedLoan(JSONObject oJSONObject) {
        iDownPayment = oJSONObject.optInt("total_downpayment");
        iAnnualPercentageRate = oJSONObject.optInt("apr");
        iAmountFinanced = oJSONObject.optInt("amount_financed");

        sFrequency = oJSONObject.optString("frequency");

        dPaymentFrequency = oJSONObject.optDouble("payment_frequency");
        dTotalInterest = oJSONObject.optDouble("total_interest");
        dTotalPayment = oJSONObject.optDouble("total_payment");

    }


}
