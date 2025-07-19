package com.oopsfirewolf.nfts.service;

import com.oopsfirewolf.nfts.dto.BuyRequest;
import com.oopsfirewolf.nfts.model.ClickLog;
import com.oopsfirewolf.nfts.model.NFT;
import com.oopsfirewolf.nfts.repository.ClickLogRepository;
import com.oopsfirewolf.nfts.repository.NFTRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NFTService {
    private static final String ACCESS_KEY = "oopsfire-secret-key";

    @Autowired
    private NFTRepository nftRepository;

    @Autowired
    private ClickLogRepository clickLogRepository;

    public List<NFT> getAllNFTs() {
        return nftRepository.findAll();
    }

    public String handleBuy(BuyRequest request, HttpServletRequest httpRequest) {
        String clientIp = httpRequest.getRemoteAddr();

        ClickLog log = new ClickLog();
        log.setWallet(request.getWallet());
        log.setNftId(request.getNftId());
        log.setAccessKey(request.getAccessKey());
        log.setIpAddress(clientIp);

        if (!ACCESS_KEY.equals(request.getAccessKey())) {
            log.setType("failed");
            clickLogRepository.save(log);
            return "❌ Invalid access key";
        }

        nftRepository.findById(request.getNftId()).ifPresent(nft -> {
            nft.setClicks(nft.getClicks() + 1);
            nftRepository.save(nft);
        });

        log.setType("success");
        clickLogRepository.save(log);

        return "✅ Buy recorded for wallet: " + request.getWallet();
    }
}
