package com.oopsfirewolf.nfts.model;

public class BuyRequest {
    private String wallet;
    private Long nftId;

    public BuyRequest() {}

    public BuyRequest(String wallet, Long nftId) {
        this.wallet = wallet;
        this.nftId = nftId;
    }

    public String getWallet() { return wallet; }
    public void setWallet(String wallet) { this.wallet = wallet; }

    public Long getNftId() { return nftId; }
    public void setNftId(Long nftId) { this.nftId = nftId; }
}

