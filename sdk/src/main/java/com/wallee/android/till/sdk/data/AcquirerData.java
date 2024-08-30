package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Represents data related to an acquirer such as the acquirer id
 */
public class AcquirerData {
    private final String acquirerId;
    private final List<TerminalApplicationConfigData> terminalApplicationConfigDataList;

    public AcquirerData(String acquirerId, List<TerminalApplicationConfigData> terminalApplicationConfigDataList) {
        this.acquirerId = acquirerId;
        this.terminalApplicationConfigDataList = terminalApplicationConfigDataList;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public List<TerminalApplicationConfigData> getTerminalApplicationConfigDataList() {
        return terminalApplicationConfigDataList;
    }

    @NonNull
    @Override
    public String toString() {
        return "AcquirerData{" +
                "acquirerId='" + acquirerId + '\'' +
                ", terminalApplicationConfigDataList=" + terminalApplicationConfigDataList +
                '}';
    }
}
