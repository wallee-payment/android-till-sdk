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

## Examples

```
//establish connection to payment service
MockResponseHandler responseHandler = new MockResponseHandler(this);
ApiConnection con = new ApiConnection(responseHandler);

//create Transaction object
BigDecimal amount = new BigDecimal(amountString);
List<LineItem> lineItems = new LineItem.ListBuilder("Widget A", amount).getCurrent().setName("bar").addTax("VAT", BigDecimal.TEN).getListBuilder().
addNext("OtherWidget", BigDecimal.ONE).getCurrent().setType(LineItemType.FEE).getListBuilder().build();
Transaction transaction = new Transaction.Builder(lineItems).setInvoiceReference(invoiceRef).setMerchantReference("MREF-123").build();

//API call
con.authorizeTransaction(transaction);
```
