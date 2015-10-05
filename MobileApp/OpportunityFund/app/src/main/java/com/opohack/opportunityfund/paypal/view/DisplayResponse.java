package com.opohack.opportunityfund.paypal.view;

import android.os.Bundle;
import android.widget.TextView;

import com.opohack.opportunityfund.paypal.R;
import com.opohack.opportunityfund.paypal.model.CalculatedLoan;
import com.opohack.opportunityfund.paypal.model.DataModelManager;

import java.util.List;

public class DisplayResponse extends OpportunityFundBaseActivity {


    private TextView mtextView;
    private CalculatedLoan mCalculatedLoan;
    private List<CalculatedLoan> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_response);
        mtextView = (TextView) findViewById(R.id.textView2);

        showContent();

    }

    private void showContent() {
        mCalculatedLoan = DataModelManager.getInstance().getCalculatedLoan();
        mtextView.setText("\n \nTotal Loan Amount Paid : " +  mCalculatedLoan.getDownPayment()
                + "\n\nAmount Financed : " + mCalculatedLoan.getAmountFinanced()
                + "\n\nPayment Frequency : " + mCalculatedLoan.getFrequency()
                + "\n\nTotal Interest : " + mCalculatedLoan.getTotalInterest()
                + "\n\nTotal Payment : " + mCalculatedLoan.getTotalPayment()
                + "\n\nAnnual Percentage Rate : " + mCalculatedLoan.getAnnualPercentageRate());
    }

}
