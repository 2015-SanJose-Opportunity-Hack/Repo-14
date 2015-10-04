package com.opohack.opportunityfund.paypal.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.opohack.opportunityfund.paypal.R;
import com.opohack.opportunityfund.paypal.model.CalculatedLoan;
import com.opohack.opportunityfund.paypal.model.DataModelManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends OpportunityFundBaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private OpportunityFundBaseActivity mInstance;
    private Spinner spinner1, spinner2;
    private EditText mLoanAmount, mTerm, mDownPayment, mLoanFee, mRate;
    private Button mCalcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInstance = new OpportunityFundBaseActivity();

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinnerfreq);
        mLoanAmount = (EditText) findViewById(R.id.input_amount);
        mTerm = (EditText) findViewById(R.id.input_term);
        mDownPayment = (EditText) findViewById(R.id.input_down_payment);
        mLoanFee = (EditText) findViewById(R.id.input_loan_fee);
        mRate = (EditText) findViewById(R.id.input_rate);

        mCalcButton = (Button) findViewById(R.id.btn_signup);

        mCalcButton.setOnClickListener(this);


        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("requested_loan", mLoanAmount.getText().toString().trim());
                    jsonObject.put("months", mTerm.getText().toString().trim());
                    jsonObject.put("downpayment_percent", mDownPayment.getText().toString().trim());
                    jsonObject.put("loan_fee", mLoanFee.getText().toString().trim());
                    jsonObject.put("interest_rate", mRate.getText().toString().trim());
                    jsonObject.put("loan_use", spinner1.getSelectedItem().toString().toLowerCase().trim());
                    jsonObject.put("frequency", spinner2.getSelectedItem().toString().toLowerCase().trim());

                    Log.e("JSON", jsonObject.toString());

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.89.211:5000/calculate", jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Volley Success", response.toString());
                            JSONArray oResponse = response.optJSONArray("message");
                            DataModelManager.getInstance().getCalculatedLoanList().clear();
                            for (int i =0; i < oResponse.length(); i++) {
                                try {
                                    CalculatedLoan calculatedLoan = new CalculatedLoan(oResponse.getJSONObject(i));
                                    DataModelManager.getInstance().getCalculatedLoanList().add(calculatedLoan);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub
                            Log.e("Volley",error.toString());
                            VolleyLog.e(TAG, error);
                        }
                    });
                    mInstance.addToRequestQueue(jsonObjectRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
