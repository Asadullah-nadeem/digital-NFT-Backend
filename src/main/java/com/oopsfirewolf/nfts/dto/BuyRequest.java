package com.oopsfirewolf.nfts.dto;

public class BuyRequest {
    private String wallet;
    private Long nftId;
    private String accessKey;

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public Long getNftId() {
        return nftId;
    }

    public void setNftId(Long nftId) {
        this.nftId = nftId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
