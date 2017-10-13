package com.theworkshop.bitu.model.wallet;

public class WalletResult {

    private String walletname;
    private int walletversion;
    private double balance;
    private double unconfirmed_balance;
    private double immature_balance;
    private int txcount;
    private int keypoololdest;
    private int keypoolsize;
    private int keypoolsize_hd_internal;
    private double paytxfee;
    private String hdmasterkeyid;

    public String getWalletname() {
        return walletname;
    }

    public void setWalletname(String walletname) {
        this.walletname = walletname;
    }

    public int getWalletversion() {
        return walletversion;
    }

    public void setWalletversion(int walletversion) {
        this.walletversion = walletversion;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getUnconfirmed_balance() {
        return unconfirmed_balance;
    }

    public void setUnconfirmed_balance(double unconfirmed_balance) {
        this.unconfirmed_balance = unconfirmed_balance;
    }

    public double getImmature_balance() {
        return immature_balance;
    }

    public void setImmature_balance(double immature_balance) {
        this.immature_balance = immature_balance;
    }

    public int getTxcount() {
        return txcount;
    }

    public void setTxcount(int txcount) {
        this.txcount = txcount;
    }

    public int getKeypoololdest() {
        return keypoololdest;
    }

    public void setKeypoololdest(int keypoololdest) {
        this.keypoololdest = keypoololdest;
    }

    public int getKeypoolsize() {
        return keypoolsize;
    }

    public void setKeypoolsize(int keypoolsize) {
        this.keypoolsize = keypoolsize;
    }

    public int getKeypoolsize_hd_internal() {
        return keypoolsize_hd_internal;
    }

    public void setKeypoolsize_hd_internal(int keypoolsize_hd_internal) {
        this.keypoolsize_hd_internal = keypoolsize_hd_internal;
    }

    public double getPaytxfee() {
        return paytxfee;
    }

    public void setPaytxfee(double paytxfee) {
        this.paytxfee = paytxfee;
    }

    public String getHdmasterkeyid() {
        return hdmasterkeyid;
    }

    public void setHdmasterkeyid(String hdmasterkeyid) {
        this.hdmasterkeyid = hdmasterkeyid;
    }

}
