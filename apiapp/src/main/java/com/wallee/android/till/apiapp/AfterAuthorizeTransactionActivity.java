package com.wallee.android.till.apiapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.wallee.android.till.sdk.data.Transaction;

public class AfterAuthorizeTransactionActivity extends AbstractActivity {
    private static final String TAG = "AfterAuth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize_transaction);

        Button buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(view -> startThirdPartyActivities());

        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(view -> finish());

        Transaction transaction = getTransaction();
        String amount = transaction.getTotalAmountIncludingTax() + " " + transaction.getCurrency();
        TextView amountLabel = findViewById(R.id.amountLabel);
        amountLabel.setText(amount);
    }

    @Override
    protected String[] getThirdPartyActions() {
        return new String[] {"com.wallee.android.till.intent.action.AUTHORIZE_TRANSACTION_AFTER"};
    }
}