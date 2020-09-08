package com.wallee.android.till.apiapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wallee.android.till.sdk.Utils;
import com.wallee.android.till.sdk.data.State;
import com.wallee.android.till.sdk.data.Transaction;

public class BeforeAuthorizeTransactionActivity extends AbstractActivity {
    private static final String TAG = "BeforeAuth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_authorize_transaction);
        if (false) {
            ScrollView view = new ScrollView(this);
            final LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            view.addView(ll);
            for (ActivityInfo activity : this.thirdPartyActivities) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(activity.name);
                ll.addView(checkBox);
            }
            Button button = new Button(this);
            button.setText("OK");
            button.setOnClickListener(e -> {
                startThirdPartyActivities();
            });
            ll.addView(button);
            setContentView(view);
        }
        //FIXME how to make this screen not show up at all? or maybe we want a summary screen?
        //setVisible(false);
        //setTitle("");
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setTheme();
        startThirdPartyActivities();
    }

    @Override
    protected void processTransaction(Transaction transaction) {
        //super.processTransaction(transaction);
        Intent intent = getIntent();
        intent.setClass(this, AfterAuthorizeTransactionActivity.class);
        Transaction processedTransaction = new TransactionBuilder(transaction).setState(State.PROCESSING).build();
        intent.putExtra(Utils.KEY_TRANSACTION_JSON, Utils.GSON.toJson(processedTransaction));
        startActivity(intent);
        finish();
    }

    @Override
    protected String[] getThirdPartyActions() {
        return new String[] {"com.wallee.android.till.intent.action.AUTHORIZE_TRANSACTION_BEFORE"};
    }
}

class TransactionBuilder extends Transaction.Builder {

    public TransactionBuilder(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Transaction.Builder setState(State state) {
        return super.setState(state);
    }
}