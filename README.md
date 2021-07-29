# Wallee Android Till SDK

## Work in Progress!
In the near future you can find the SDK for building Payment Terminal Apps here.

## Who is this for?
Merchants that want to build their own Checkout/Till App to run on Android devices like 
e.g. the [A920](https://www.pax.us/portfolio_page/a920/)

![A920](https://www.pax.us/wp-content/uploads/2019/09/A920-SMB.jpg)

## Why would I want to use this SDK
This SDK integrates access to terminal hardware like e.g. the card/stripe/nfc reader
and allows for easy payment processing with wallee.

## Example

```
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //dummy example implementation of an API response handler
        ResponseHandler responseHandler = new ResponseHandler() {
            @Override
            public void authorizeTransactionReply(Transaction transaction) {
                Log.i("Main", "authorizeTransactionReply:  + transaction");
            }
        };

        //create the client with a reference to the response handler
        ApiClient client = new ApiClient(responseHandler);
        //and establish connection to API service
        client.bind(this);

        //create your Transaction object
        BigDecimal amount = new BigDecimal("12.34");
        List<LineItem> lineItems = new LineItem.ListBuilder("Widget A", amount).build();
        Transaction transaction = new Transaction.Builder(lineItems).setInvoiceReference("IREF-123").setMerchantReference("MREF-123").build();

        //and call the API service with your Transaction
        try {
            client.authorizeTransaction(transaction);
        } catch(RemoteException ex) {
            Log.e(TAG, "API call failed", ex);
        }
```

## Kiosk mode

For merchants who want to limit device access to their application only, there is a possibility to configure device to such setting. The merchant application would require to define additional parameters in `AndroidManifest.xml`:

```
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

For third party applications which want to modify transaction before or after it is fully processed, there is a posibility to do so. Such application would require to define additional parameters in `AndroidManifest.xml`.

For intercepting transaction before it is processed:

```
<activity
    android:name="com.wallee.android.till.YourActivityThatModifiesTransactionBeforeProcessing">
    <intent-filter>
        <action android:name="com.wallee.android.AUTHORIZE_TRANSACTION_BEFORE" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

For intercepting transaction after it is processed:

```
<activity
    android:name="com.wallee.android.till.YourActivityThatModifiesTransactionAfterProcessing">
    <intent-filter>
        <action android:name="com.wallee.android.AUTHORIZE_TRANSACTION_AFTER" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

Such activity would get the transaction object with `Intent`. The object can be retrieved with the help of `Utils` class, by using extras from this `Intent`.

The activity will be started by the SDK itself, when appropriate processing step will occur.

The activity would also require to send result back.

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
    implementation 'com.wallee.android.till:sdk:0.9.13'
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