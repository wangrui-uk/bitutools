package com.theworkshop.bitu.model.wallet;

import com.theworkshop.bitu.model.Response;

public class Wallet extends Response {

    private WalletResult result;

    public WalletResult getResult() {
        return result;
    }

    public void setResult(WalletResult result) {
        this.result = result;
    }

}
