package com.wallee.android.till.sdk.data;

import java.util.List;

public class Ep2TerminalConfigData {
    private String addTrmCap;
    private String autoDeclRef;
    private String cardRdType;
    private CommAddressGateWay commAddressGateWay;
    private CommAddressPMS commAddressPMS;
    private CommAddressSCConf commAddressSCConf;
    private String ctlessInd;
    private List<Ep2CtlessTrmCapPerKernel> setCtlessTrmCap;
    private String dataSubmMaxRetry;
    private String dSub;
    private String dataSubmRetryDel;
    private String dataSubmTime;
    private String dataSubmTrigg;
    private String dataTransMaxRetry;
    private String dataTransRetryDel;
    private String dataTransTime;
    private String dataTransTrigg;
    private String dccProvider;
    private String defTrxCurrC;
    private String maxStor;
    private String mctId;
    private String phonePrfx;
    private String pmsId;
    private String pmsPubKey;
    private String revRetryDelay;
    private String scConfTime;
    private String scId;
    private String scIntConf;
    private String scPubKey;
    private String submInt;
    private String suppPhone;
    private String trmCap;
    private String trmCntryC;
    private String trmID;
    private String trmLng;
    private String trmRMCap;
    private String trmTrxFctCap;
    private String trmType;
    private String toDataSubSrv;
    private String toInitSrv;
    private String toAuthSrv;
    private String toCardIn;
    private String toCardRem;
    private String toConfTrx;
    private String toDatEntry;
    private String toFB;
    private String toICC;
    private String toPMS;
    private String toPosTrxRq;
    private String toRmdr;
    private String toSCReq;
    private String toTrxTrans;
    private String trxSubmLim;
    private String trxTransLim;
    private String transInt;
    private String usrRetryCnt;
    private String versSW;

    private Ep2TerminalConfigData(Builder builder) {
        this.addTrmCap = builder.addTrmCap;
        this.autoDeclRef = builder.autoDeclRef;
        this.cardRdType = builder.cardRdType;
        this.commAddressGateWay = builder.commAddressGateWay;
        this.commAddressPMS = builder.commAddressPMS;
        this.commAddressSCConf = builder.commAddressSCConf;
        this.ctlessInd = builder.ctlessInd;
        this.setCtlessTrmCap = builder.setCtlessTrmCap;
        this.dataSubmMaxRetry = builder.dataSubmMaxRetry;
        this.dSub = builder.dSub;
        this.dataSubmRetryDel = builder.dataSubmRetryDel;
        this.dataSubmTime = builder.dataSubmTime;
        this.dataSubmTrigg = builder.dataSubmTrigg;
        this.dataTransMaxRetry = builder.dataTransMaxRetry;
        this.dataTransRetryDel = builder.dataTransRetryDel;
        this.dataTransTime = builder.dataTransTime;
        this.dataTransTrigg = builder.dataTransTrigg;
        this.dccProvider = builder.dccProvider;
        this.defTrxCurrC = builder.defTrxCurrC;
        this.maxStor = builder.maxStor;
        this.mctId = builder.mctId;
        this.phonePrfx = builder.phonePrfx;
        this.pmsId = builder.pmsId;
        this.pmsPubKey = builder.pmsPubKey;
        this.revRetryDelay = builder.revRetryDelay;
        this.scConfTime = builder.scConfTime;
        this.scId = builder.scId;
        this.scIntConf = builder.scIntConf;
        this.scPubKey = builder.scPubKey;
        this.submInt = builder.submInt;
        this.suppPhone = builder.suppPhone;
        this.trmCap = builder.trmCap;
        this.trmCntryC = builder.trmCntryC;
        this.trmID = builder.trmID;
        this.trmLng = builder.trmLng;
        this.trmRMCap = builder.trmRMCap;
        this.trmTrxFctCap = builder.trmTrxFctCap;
        this.trmType = builder.trmType;
        this.toDataSubSrv = builder.toDataSubSrv;
        this.toInitSrv = builder.toInitSrv;
        this.toAuthSrv = builder.toAuthSrv;
        this.toCardIn = builder.toCardIn;
        this.toCardRem = builder.toCardRem;
        this.toConfTrx = builder.toConfTrx;
        this.toDatEntry = builder.toDatEntry;
        this.toFB = builder.toFB;
        this.toICC = builder.toICC;
        this.toPMS = builder.toPMS;
        this.toPosTrxRq = builder.toPosTrxRq;
        this.toRmdr = builder.toRmdr;
        this.toSCReq = builder.toSCReq;
        this.toTrxTrans = builder.toTrxTrans;
        this.trxSubmLim = builder.trxSubmLim;
        this.trxTransLim = builder.trxTransLim;
        this.transInt = builder.transInt;
        this.usrRetryCnt = builder.usrRetryCnt;
        this.versSW = builder.versSW;
    }


    public String getAddTrmCap() {
        return addTrmCap;
    }

    public String getAutoDeclRef() {
        return autoDeclRef;
    }

    public String getCardRdType() {
        return cardRdType;
    }

    public CommAddressGateWay getCommAddressGateWay() {
        return commAddressGateWay;
    }

    public CommAddressPMS getCommAddressPMS() {
        return commAddressPMS;
    }

    public CommAddressSCConf getCommAddressSCConf() {
        return commAddressSCConf;
    }

    public String getCtlessInd() {
        return ctlessInd;
    }

    public List<Ep2CtlessTrmCapPerKernel> getSetCtlessTrmCap() {
        return setCtlessTrmCap;
    }

    public String getDataSubmMaxRetry() {
        return dataSubmMaxRetry;
    }

    public String getdSub() {
        return dSub;
    }

    public String getDataSubmRetryDel() {
        return dataSubmRetryDel;
    }

    public String getDataSubmTime() {
        return dataSubmTime;
    }

    public String getDataSubmTrigg() {
        return dataSubmTrigg;
    }

    public String getDataTransMaxRetry() {
        return dataTransMaxRetry;
    }

    public String getDataTransRetryDel() {
        return dataTransRetryDel;
    }

    public String getDataTransTime() {
        return dataTransTime;
    }

    public String getDataTransTrigg() {
        return dataTransTrigg;
    }

    public String getDccProvider() {
        return dccProvider;
    }

    public String getDefTrxCurrC() {
        return defTrxCurrC;
    }

    public String getMaxStor() {
        return maxStor;
    }

    public String getMctId() {
        return mctId;
    }

    public String getPhonePrfx() {
        return phonePrfx;
    }

    public String getPmsId() {
        return pmsId;
    }

    public String getPmsPubKey() {
        return pmsPubKey;
    }

    public String getRevRetryDelay() {
        return revRetryDelay;
    }

    public String getScConfTime() {
        return scConfTime;
    }

    public String getScId() {
        return scId;
    }

    public String getScIntConf() {
        return scIntConf;
    }

    public String getScPubKey() {
        return scPubKey;
    }

    public String getSubmInt() {
        return submInt;
    }

    public String getSuppPhone() {
        return suppPhone;
    }

    public String getTrmCap() {
        return trmCap;
    }

    public String getTrmCntryC() {
        return trmCntryC;
    }

    public String getTrmID() {
        return trmID;
    }

    public String getTrmLng() {
        return trmLng;
    }

    public String getTrmRMCap() {
        return trmRMCap;
    }

    public String getTrmTrxFctCap() {
        return trmTrxFctCap;
    }

    public String getTrmType() {
        return trmType;
    }

    public String getToDataSubSrv() {
        return toDataSubSrv;
    }

    public String getToInitSrv() {
        return toInitSrv;
    }

    public String getToAuthSrv() {
        return toAuthSrv;
    }

    public String getToCardIn() {
        return toCardIn;
    }

    public String getToCardRem() {
        return toCardRem;
    }

    public String getToConfTrx() {
        return toConfTrx;
    }

    public String getToDatEntry() {
        return toDatEntry;
    }

    public String getToFB() {
        return toFB;
    }

    public String getToICC() {
        return toICC;
    }

    public String getToPMS() {
        return toPMS;
    }

    public String getToPosTrxRq() {
        return toPosTrxRq;
    }

    public String getToRmdr() {
        return toRmdr;
    }

    public String getToSCReq() {
        return toSCReq;
    }

    public String getToTrxTrans() {
        return toTrxTrans;
    }

    public String getTrxSubmLim() {
        return trxSubmLim;
    }

    public String getTrxTransLim() {
        return trxTransLim;
    }

    public String getTransInt() {
        return transInt;
    }

    public String getUsrRetryCnt() {
        return usrRetryCnt;
    }

    public String getVersSW() {
        return versSW;
    }

    public static class Builder {
        private String addTrmCap;
        private String autoDeclRef;
        private String cardRdType;
        private CommAddressGateWay commAddressGateWay;
        private CommAddressPMS commAddressPMS;
        private CommAddressSCConf commAddressSCConf;
        private String ctlessInd;
        private List<Ep2CtlessTrmCapPerKernel> setCtlessTrmCap;
        private String dataSubmMaxRetry;
        private String dSub;
        private String dataSubmRetryDel;
        private String dataSubmTime;
        private String dataSubmTrigg;
        private String dataTransMaxRetry;
        private String dataTransRetryDel;
        private String dataTransTime;
        private String dataTransTrigg;
        private String dccProvider;
        private String defTrxCurrC;
        private String maxStor;
        private String mctId;
        private String phonePrfx;
        private String pmsId;
        private String pmsPubKey;
        private String revRetryDelay;
        private String scConfTime;
        private String scId;
        private String scIntConf;
        private String scPubKey;
        private String submInt;
        private String suppPhone;
        private String trmCap;
        private String trmCntryC;
        private String trmID;
        private String trmLng;
        private String trmRMCap;
        private String trmTrxFctCap;
        private String trmType;
        private String toDataSubSrv;
        private String toInitSrv;
        private String toAuthSrv;
        private String toCardIn;
        private String toCardRem;
        private String toConfTrx;
        private String toDatEntry;
        private String toFB;
        private String toICC;
        private String toPMS;
        private String toPosTrxRq;
        private String toRmdr;
        private String toSCReq;
        private String toTrxTrans;
        private String trxSubmLim;
        private String trxTransLim;
        private String transInt;
        private String usrRetryCnt;
        private String versSW;

        public Builder setAddTrmCap(String addTrmCap) {
            this.addTrmCap = addTrmCap;
            return this;
        }

        public Builder setAutoDeclRef(String autoDeclRef) {
            this.autoDeclRef = autoDeclRef;
            return this;
        }

        public Builder setCardRdType(String cardRdType) {
            this.cardRdType = cardRdType;
            return this;
        }

        public Builder setCommAddressGateWay(CommAddressGateWay commAddressGateWay) {
            this.commAddressGateWay = commAddressGateWay;
            return this;
        }

        public Builder setCommAddressPMS(CommAddressPMS commAddressPMS) {
            this.commAddressPMS = commAddressPMS;
            return this;
        }

        public Builder setCommAddressSCConf(CommAddressSCConf commAddressSCConf) {
            this.commAddressSCConf = commAddressSCConf;
            return this;
        }

        public Builder setCtlessInd(String ctlessInd) {
            this.ctlessInd = ctlessInd;
            return this;
        }

        public Builder setSetCtlessTrmCap(List<Ep2CtlessTrmCapPerKernel> setCtlessTrmCap) {
            this.setCtlessTrmCap = setCtlessTrmCap;
            return this;
        }

        public Builder setDataSubmMaxRetry(String dataSubmMaxRetry) {
            this.dataSubmMaxRetry = dataSubmMaxRetry;
            return this;
        }

        public Builder setDSub(String dSub) {
            this.dSub = dSub;
            return this;
        }

        public Builder setDataSubmRetryDel(String dataSubmRetryDel) {
            this.dataSubmRetryDel = dataSubmRetryDel;
            return this;
        }

        public Builder setDataSubmTime(String dataSubmTime) {
            this.dataSubmTime = dataSubmTime;
            return this;
        }

        public Builder setDataSubmTrigg(String dataSubmTrigg) {
            this.dataSubmTrigg = dataSubmTrigg;
            return this;
        }

        public Builder setDataTransMaxRetry(String dataTransMaxRetry) {
            this.dataTransMaxRetry = dataTransMaxRetry;
            return this;
        }

        public Builder setDataTransRetryDel(String dataTransRetryDel) {
            this.dataTransRetryDel = dataTransRetryDel;
            return this;
        }

        public Builder setDataTransTime(String dataTransTime) {
            this.dataTransTime = dataTransTime;
            return this;
        }

        public Builder setDataTransTrigg(String dataTransTrigg) {
            this.dataTransTrigg = dataTransTrigg;
            return this;
        }

        public Builder setDccProvider(String dccProvider) {
            this.dccProvider = dccProvider;
            return this;
        }

        public Builder setDefTrxCurrC(String defTrxCurrC) {
            this.defTrxCurrC = defTrxCurrC;
            return this;
        }

        public Builder setMaxStor(String maxStor) {
            this.maxStor = maxStor;
            return this;
        }

        public Builder setMctId(String mctId) {
            this.mctId = mctId;
            return this;
        }

        public Builder setPhonePrfx(String phonePrfx) {
            this.phonePrfx = phonePrfx;
            return this;
        }

        public Builder setPmsId(String pmsId) {
            this.pmsId = pmsId;
            return this;
        }

        public Builder setPmsPubKey(String pmsPubKey) {
            this.pmsPubKey = pmsPubKey;
            return this;
        }

        public Builder setRevRetryDelay(String revRetryDelay) {
            this.revRetryDelay = revRetryDelay;
            return this;
        }

        public Builder setScConfTime(String scConfTime) {
            this.scConfTime = scConfTime;
            return this;
        }

        public Builder setScId(String scId) {
            this.scId = scId;
            return this;
        }

        public Builder setScIntConf(String scIntConf) {
            this.scIntConf = scIntConf;
            return this;
        }

        public Builder setScPubKey(String scPubKey) {
            this.scPubKey = scPubKey;
            return this;
        }

        public Builder setSubmInt(String submInt) {
            this.submInt = submInt;
            return this;
        }

        public Builder setSuppPhone(String suppPhone) {
            this.suppPhone = suppPhone;
            return this;
        }

        public Builder setTrmCap(String trmCap) {
            this.trmCap = trmCap;
            return this;
        }

        public Builder setTrmCntryC(String trmCntryC) {
            this.trmCntryC = trmCntryC;
            return this;
        }

        public Builder setTrmID(String trmID) {
            this.trmID = trmID;
            return this;
        }

        public Builder setTrmLng(String trmLng) {
            this.trmLng = trmLng;
            return this;
        }

        public Builder setTrmRMCap(String trmRMCap) {
            this.trmRMCap = trmRMCap;
            return this;
        }

        public Builder setTrmTrxFctCap(String trmTrxFctCap) {
            this.trmTrxFctCap = trmTrxFctCap;
            return this;
        }

        public Builder setTrmType(String trmType) {
            this.trmType = trmType;
            return this;
        }

        public Builder setToDataSubSrv(String toDataSubSrv) {
            this.toDataSubSrv = toDataSubSrv;
            return this;
        }

        public Builder setToInitSrv(String toInitSrv) {
            this.toInitSrv = toInitSrv;
            return this;
        }

        public Builder setToAuthSrv(String toAuthSrv) {
            this.toAuthSrv = toAuthSrv;
            return this;
        }

        public Builder setToCardIn(String toCardIn) {
            this.toCardIn = toCardIn;
            return this;
        }

        public Builder setToCardRem(String toCardRem) {
            this.toCardRem = toCardRem;
            return this;
        }

        public Builder setToConfTrx(String toConfTrx) {
            this.toConfTrx = toConfTrx;
            return this;
        }

        public Builder setToDatEntry(String toDatEntry) {
            this.toDatEntry = toDatEntry;
            return this;
        }

        public Builder setToFB(String toFB) {
            this.toFB = toFB;
            return this;
        }

        public Builder setToICC(String toICC) {
            this.toICC = toICC;
            return this;
        }

        public Builder setToPMS(String toPMS) {
            this.toPMS = toPMS;
            return this;
        }

        public Builder setToPosTrxRq(String toPosTrxRq) {
            this.toPosTrxRq = toPosTrxRq;
            return this;
        }

        public Builder setToRmdr(String toRmdr) {
            this.toRmdr = toRmdr;
            return this;
        }

        public Builder setToSCReq(String toSCReq) {
            this.toSCReq = toSCReq;
            return this;
        }

        public Builder setToTrxTrans(String toTrxTrans) {
            this.toTrxTrans = toTrxTrans;
            return this;
        }

        public Builder setTrxSubmLim(String trxSubmLim) {
            this.trxSubmLim = trxSubmLim;
            return this;
        }

        public Builder setTrxTransLim(String trxTransLim) {
            this.trxTransLim = trxTransLim;
            return this;
        }

        public Builder setTransInt(String transInt) {
            this.transInt = transInt;
            return this;
        }

        public Builder setUsrRetryCnt(String usrRetryCnt) {
            this.usrRetryCnt = usrRetryCnt;
            return this;
        }

        public Builder setVersSW(String versSW) {
            this.versSW = versSW;
            return this;
        }

        public Ep2TerminalConfigData build() {
            return new Ep2TerminalConfigData(this);
        }
    }
}
