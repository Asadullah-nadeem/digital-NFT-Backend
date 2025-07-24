package com.oopsfirewolf.nfts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopsfirewolf.nfts.dto.BuyRequest;
import com.oopsfirewolf.nfts.model.ClickLog;
import com.oopsfirewolf.nfts.repository.ClickLogRepository;
import com.oopsfirewolf.nfts.repository.NFTRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ClicklogService {
    private static final String ACCESS_KEY = "A@$*42343424";

    @Autowired
    private NFTRepository nftRepository;

    @Autowired
    private ClickLogRepository clickLogRepository;


    public List<ClickLog> getAllNFTs() {
        return clickLogRepository.findAll();
    }


    public String handleBuy(BuyRequest request, HttpServletRequest httpRequest) {
        String clientIp = httpRequest.getRemoteAddr();

        ClickLog log = new ClickLog();
        log.setNftId(request.getNftId());
        log.setWallet(request.getWallet());
        log.setAccessKey(request.getAccessKey());
        log.setIpAddress(clientIp);

        // if (!ACCESS_KEY.equals(request.getAccessKey())) {
        //     log.setType("failed");
        //     clickLogRepository.save(log);
        //     return "❌ Invalid access key";
        // }
        if(ACCESS_KEY.equals(request.getAccessKey())){
          log.setType("Success");
          clickLogRepository.save(log);
          nftRepository.findById(request.getNftId()).ifPresent(nft -> {
            nft.setClicks(nft.getClicks() + 1);
            nftRepository.save(nft);
          });
         }else if(!ACCESS_KEY.equals(request.getAccessKey())){
            log.setType("failed");
            clickLogRepository.save(log);
            return "Invalid access key";
        }
        return "✅ Buy recorded for wallet: " + request.getWallet();

    }
}
