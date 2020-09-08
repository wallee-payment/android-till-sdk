package com.wallee.android.till.apiapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wallee.android.till.sdk.ReturnCode;
import com.wallee.android.till.sdk.Utils;
import com.wallee.android.till.sdk.data.Transaction;

/**
 * Abstract class that implements support for 3rd party apps.
 */
public abstract class AbstractActivity extends AppCompatActivity {
    private static final String TAG = "AbstractAct";
    protected List<ActivityInfo> thirdPartyActivities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:" + getClass().getSimpleName());
        this.thirdPartyActivities = getThirdPartyActivities(getThirdPartyActions());
    }

    protected List<ActivityInfo> getThirdPartyActivities(String[] actions) {
        //how to best implement dynamic callbacks to coupon and other apps???
        List<ActivityInfo> result = new ArrayList<>();

        //explicit lookup for known applications
        List<ApplicationInfo> apps = getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo info : apps) {
            //Log.d(TAG, info.packageName);
            if (false) {//match known packages//FIXME make list of known packages configurable
                result.add(new Intent().setClassName(info.packageName, info.className).resolveActivityInfo(getPackageManager(), PackageManager.MATCH_ALL));
            }
        }

        for (String action : actions) {
            //implicit lookup by intent
            Intent intent = new Intent();
            intent.setAction(action);
            List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
            Collections.sort(activities, new Comparator<ResolveInfo>() {
                @Override
                public int compare(ResolveInfo o1, ResolveInfo o2) {
                    return o1.activityInfo.packageName.compareTo(o2.activityInfo.packageName);
                }
            });//make execution deterministic by sorting alphabetically
            for (ResolveInfo info : activities) {
                result.add(info.activityInfo);
            }
        }
        return result;
    }

    protected void startThirdPartyActivities() {
        if (thirdPartyActivities != null && thirdPartyActivities.size() > 0) {
            startThirdPartyActivity(0, getTransaction());
        } else {
            processTransaction(getTransaction());
        }
    }

    private void startThirdPartyActivity(int index, Transaction transaction) {
        ActivityInfo info = this.thirdPartyActivities.get(index);
        Log.d(TAG, "Starting: " + info.name + ":" + index + "\n" + Utils.GSON.toJson(transaction));
        Intent explicit = new Intent();
        explicit.setClassName(info.packageName, info.name);
        explicit.putExtra(Utils.KEY_TRANSACTION_JSON, Utils.GSON.toJson(transaction));
        startActivityForResult(explicit, index);
    }

    protected Transaction getTransaction() {
        Message msg = getIntent().getParcelableExtra("msg");
        if (msg != null) {
            Bundle bundle = msg.getData();
            Transaction data = Utils.GSON.fromJson(bundle.getString(Utils.KEY_TRANSACTION_JSON), Transaction.class);
            return data;
        }
        return null;
    }

    protected void processTransaction(Transaction transaction) {
        Message reply = Message.obtain();
        Messenger messenger = getIntent().getParcelableExtra("replyTo");
        reply.arg1 = 0;
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TRANSACTION_JSON, Utils.GSON.toJson(transaction));
        reply.setData(bundle);
        try {
            messenger.send(reply);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message", e);
        }

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + requestCode + ":" + resultCode);

        ReturnCode rc = ReturnCode.values()[resultCode];
        if (rc == ReturnCode.ERROR) {
            //implement error handling
        } else if (rc == ReturnCode.CANCELED) {
            finish();
            return;
        }
        Transaction t = Utils.getTransaction(getIntent());//FIXME check state of transaction before allowing further modifications
        final Transaction transaction;
        if (data != null) {// this means the 3rd party returned an updated transaction object
            transaction = Utils.GSON.fromJson(data.getStringExtra(Utils.KEY_TRANSACTION_JSON), Transaction.class);
            getIntent().putExtra(Utils.KEY_TRANSACTION_JSON, data.getStringExtra(Utils.KEY_TRANSACTION_JSON));
        } else if (getIntent().getStringExtra(Utils.KEY_TRANSACTION_JSON) != null) {
            transaction = Utils.getTransaction(getIntent());
        } else {
            transaction = getTransaction();
        }

        Spanned text = Html.fromHtml("<b>" + transaction.getTotalAmountIncludingTax() + " " + transaction.getCurrency() +"</b><br>" + transaction.getMerchantReference() + "<br>" + transaction.getInvoiceReference());
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        if (this.thirdPartyActivities.size() > requestCode + 1) {
            startThirdPartyActivity(requestCode + 1, transaction);
        } else {
            processTransaction(transaction);
        }
    }

    /**
     * The order of the actions in the array, reflects the order in which they will be called.
     * @return all Intent actions that will be tried to execute.
     */
    protected abstract String[] getThirdPartyActions();
}
