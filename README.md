# SDK for Wallee's Android Till Interface (ATI)

## Integration Variants

This document describes the Android Till Interface (ATI), which is one out of three different interfaces which are available to integrate with a Wallee terminal.

* **Android Till Interface (ATI)**: Integrators are recommended to use the Android Till Interface if their Till application is installed directly on an Android-based terminal running Wallee Payment Application. This way, the till app can directly talk to the terminal software (via [android.os.Message](https://developer.android.com/reference/android/os/Message)) and exchange transaction objects via Till SDK library without consideration for Till Protocol details. The benefit is that one can use our **Wallee Android Till SDK** to interact with the terminal software. If the till app is not running on the terminal, then the Android Till Interface does not work.

* **Local Till Interface [(LTI)](https://lti.docs.wallee.com/)**: Integrators should use the LTI if the till application connects to the terminal via a local area network. The most common use case is if the terminal and till are connected to the same LAN at the merchant's site. The benefit is a fast connection as both, the till application and the terminal device, are in the same local network. One drawback is that the terminal's IP address needs to be known. It is also possible to install till applications directly on the device and use LTI via localhost. Nevertheless, if a till application is installed on an Android terminal, then using the **Android Till Interface (ATI)** is recommended.

* **Cloud Till Interface [(CTI)](https://app-wallee.com/de-de/doc/payment/terminal)**: By using the cloud interface, an integrator can use a public reachable web API to integrate his application or web based service with a payment terminal. The benefit is that the till app does not need to know where the device is located and it works from any network. However, there is a higher delay compared to other integration methods because the communication happens via a central server.


## Who is this for?
Merchants that want to build their own Checkout/Till App to run on Android payment terminals like
e.g. the [A920pro](https://terminal-shop.wallee.com/de/product/pax-a920pro-wallee/)

## Why would I want to use this SDK
- Allows to integrate with Wallee Payment Application to trigger card payments without knowledge of till communication protocol
- Provides easy and quick integration with transaction classes and objects


### ApiClient Class

The [ApiClient](sdk/src/main/java/com/wallee/android/till/sdk/ApiClient.java) class serves as the primary interface for performing till requests and checking SDK compatibility. Below are the available methods:

- **checkApiServiceCompatibility**: Verify SDK compatibility.
- **authorizeTransaction**: Perform purchase, credit, and reservation transactions.
- **completeTransaction**: Complete reservations.
- **voidTransaction**: Cancel reservations.
- **cancelLastTransactionOperation**: Cancel the last transaction.
- **executeSubmission**: Initiate submission.
- **executeTransmission**: Initiate transmission.
- **executeFinalBalance**: Perform final balance.
- **executeGeneratePanToken**: Generate a card PAN token.
- **getPinPadInformation**: Retrieve pinpad information (terminal ID, device serial number, space ID, merchant ID, and name).
- **executeConfiguration**: Initiate configuration request (siConfigRequest) .
- **executeInitialisation**: Initiate initialisation request (siInitRequest).

## How to use the Android Till Interface SDK

## Example Application


A minimal Example which performs a simple transaction is shown below. For a detailed example, have a look at our [Till Sample App](https://github.com/wallee-payment/android-till-sample-app).



## Example
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnTransaction"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Transaction"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

```java
public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dummy example implementation of an API response handler
        ResponseHandler responseHandler = new ResponseHandler() {

            @Override
            public void authorizeTransactionReply(TransactionResponse response) {
                Log.i("Main", "authorizeTransactionReply:  + transaction");
            }
        };

        //create the client with a reference to the response handler
        final ApiClient client = new ApiClient(responseHandler);
        //and establish connection to API service
        client.bind(this);


        final Button btnTransaction = findViewById(R.id.btnTransaction);
        btnTransaction.setOnClickListener(v -> {
            Currency currency = Currency.getInstance("CHF");
            BigDecimal amount = new BigDecimal("15.00");
            List<LineItem> lineItems = new LineItem.ListBuilder("Widget A", amount).build();
            Transaction transaction = new Transaction.Builder(lineItems).setInvoiceReference("IREF-123").setMerchantReference("MREF-123").setCurrency(currency).build();
            try {
                client.authorizeTransaction(transaction);
            } catch (RemoteException ex) {
                Log.e(TAG, "API call failed", ex);
            }
        });

    }
}
```

## Handling Responses

To handle responses, extend the `ResponseHandler` class from the Till SDK to receive requests' responses

#### Response Objects:

- **TransactionResponse**
- **TransactionCompletionResponse**
- **TransactionVoidResponse**:
- **CancelationResult**:
- **cancelLastTransactionOperation**:
- **SubmissionResult**:
- **TransmissionResult**:
- **FinalBalanceResult**:
- **GeneratePanTokenResponse**:
- **GetPinpadInformationResponse**:


##### Example
```java
class WalleeResponseHandler extends ResponseHandler {
    private final Context context;

    WalleeResponseHandler(Context context) {
        this.context = context;
    }

    @Override
    public void authorizeTransactionReply(TransactionResponse transactionResponse) {
        Intent intent = new Intent(context, TransactionResponseActivity.class);
        intent.putExtras(Utils.toBundle(transactionResponse));
        context.startActivity(intent);
    }

}
```



## Kiosk mode

For merchants who want to limit device access to their application only, there is a possibility to configure device to such setting. The merchant application would require to define additional parameters in `AndroidManifest.xml`:

```xml
<activity
    android:name="com.wallee.android.till.YourLaunchActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <action android:name="com.wallee.android.TILL_APPLICATION" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

## Transactions interception

For third-party applications which want to modify transactions before or after it is fully processed, there is a possibility to do so. Such an application needs to define additional parameters in `AndroidManifest.xml`.

For intercepting transactions before it is processed:

```xml
<activity
    android:name="com.wallee.android.till.YourActivityThatModifiesTransactionBeforeProcessing">
    <intent-filter>
        <action android:name="com.wallee.android.AUTHORIZE_TRANSACTION_BEFORE" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

For intercepting transactions after it is processed:

```xml
<activity
    android:name="com.wallee.android.till.YourActivityThatModifiesTransactionAfterProcessing">
    <intent-filter>
        <action android:name="com.wallee.android.AUTHORIZE_TRANSACTION_AFTER" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

Such activity would get the transaction object with `Intent`. The object can be retrieved with the help of the `Utils` class, by using extras from this `Intent`.

The activity will be started by the SDK itself when an appropriate processing step will occur.

The activity would also require sending the result back.

In case of success:

```
setResult(Activity.RESULT_OK, intentWithBundle);
finish();
```

And in case of error:

```
setResult(Activity.RESULT_CANCELED);
finish();
```

## How to get it

Copy and paste this inside your build.gradle dependencies block.

```
dependencies {
    implementation 'com.wallee.android.till:sdk:0.9.23'
}
```

You will also need to add the SDK repository to your project build.gradle file.

```
allprojects {
    repositories {
        google()
        maven {
            url "https://gitlab.com/api/v4/projects/21028526/packages/maven"
        }
    }
}
```

## ProGuard

```
-keepattributes *Annotation*, Signature
-dontwarn sun.misc.**
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.wallee.android.till.sdk.data.** { *; }
```


## Send logs to Paydroid VSD

```
Bind for the logs:
TillLog.getInstance().bind(this);

Unbind for the logs:
TillLog.getInstance().unbind(this);

TillLog.debug("VSD Start Transaction of amount  -> " + amountString);
TillLog class supports all types of logs (Debug, Verbose, Warning etc).
```
### Invoke Wallee Settings Menu

```
Call method:
Utils.openSettings(getApplicationContext());
```

### Enable and Disable System Navigation Bar

```
Call methods:
Utils.enableSystemBar(getApplicationContext());

Utils.disableSystemBar(getApplicationContext());
```

## Overlay Permission for Android 10 Devices

In devices running Android 10 (SDK version 29) or higher, it's important to allow the Android overlay permission (`Allow display over other apps`). This permission is required to return to the third-party app after completing a transaction.

### Usage

To implement overlay permission in your Android app:

1. Include the `requestOverlayPermission()` method in your codebase.
2. Call this method before initiating any transactions in your Android app.
3. Once the permission is granted, your app is ready to execute transactions.

#### Manifest

```xml
<manifest>
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
</manifest>
```

#### Code Snippet

```
private void requestOverlayPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
        }
    }
}
```
## Home Application

The Wallee Paydroid application serves as the home application on android devices, we strongly advise against using third-party apps as the home application.


## ATI SDK Feature Parity with LTI

At the current state, the Android Till Interface SDK supports only a subset of all requests provided by the [LTI](https://lti.docs.wallee.com). The following LTI requests are supported:

- [financialTrxRequest](https://lti.docs.wallee.com/lti_2.46/#financialtrx-message)
- [cancelReservationRequest](https://lti.docs.wallee.com/lti_2.46/#cancelreservation-message)
- [reversalRequest](https://lti.docs.wallee.com/lti_2.46/#reversal-message)
- [miSubmissionRequest](https://lti.docs.wallee.com/lti_2.46/#misubmission-message)
- [beTransmissionRequest](https://lti.docs.wallee.com/lti_2.46/#betransmission-message)
- [beFinalBalanceRequest](https://lti.docs.wallee.com/lti_2.46/#befinalbalance-message)
- [generatePanTokenRequest](https://lti.docs.wallee.com/lti_2.46/#generatepantoken-message)
- [pinpadInformationRequest](https://lti.docs.wallee.com/lti_2.46/#pinpadinformation-message)
- [siConfigRequest](https://lti.docs.wallee.com/lti_2.46/#siconfig-message)
- [siInitRequest](https://lti.docs.wallee.com/lti_2.46/#siinit-message)


### Please find the error codes in  [ERROR-CODES](ERRORCODES.md)

