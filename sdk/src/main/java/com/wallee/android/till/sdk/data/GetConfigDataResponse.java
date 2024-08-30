package com.wallee.android.till.sdk.data;

import static com.wallee.android.till.sdk.data.Utils.requireNonNull;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * The terminal configuration response data from {@link com.wallee.android.till.sdk.ApiClient#getConfigData()} API call.
 */
public class GetConfigDataResponse {
    private final State state;
    private final ResultCode resultCode;
    private final List<AcquirerData> acquirerDataList;
    private final Ep2TerminalConfigData ep2Tcd;
    private final String ep2Version;
    private final List<CurrencyItem> currencyList;
    private final Brand brand;

    public GetConfigDataResponse(@NonNull State state, @NonNull ResultCode resultCode, List<AcquirerData> acquirerDataList, Ep2TerminalConfigData ep2RTcd, String ep2Version, List<CurrencyItem> currencyList, Brand brand){
        this.state = requireNonNull(state, "state");
        this.resultCode = requireNonNull(resultCode, "resultCode");
        this.acquirerDataList = acquirerDataList;
        this.ep2Tcd = ep2RTcd;
        this.ep2Version = ep2Version;
        this.currencyList = currencyList;
        this.brand = brand;
    }

    @NonNull
    public State getState(){
        return state;
    }

    @NonNull
    public ResultCode getResultCode(){
        return resultCode;
    }

    public List<AcquirerData> getAcquirerDataList(){
        return acquirerDataList;
    }

    public Ep2TerminalConfigData getEp2Tcd(){
        return ep2Tcd;
    }

    public String getEp2Version(){
        return ep2Version;
    }

    public List<CurrencyItem> getCurrencyList() {
        return currencyList;
    }

    public Brand getBrand() {
        return brand;
    }

    @NonNull
    @Override
    public String toString() {
        return "GetConfigDataResponse{" +
                "state=" + state +
                ", resultCode=" + resultCode +
                ", acquirerDataList=" + acquirerDataList +
                ", ep2Tcd=" + ep2Tcd +
                ", ep2Version='" + ep2Version + '\'' +
                ", currencyList=" + currencyList +
                ", brand=" + brand +
                '}';
    }
}
