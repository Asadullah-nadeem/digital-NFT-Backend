package com.oopsfirewolf.nfts.controller;

import com.oopsfirewolf.nfts.dto.BuyRequest;
import com.oopsfirewolf.nfts.model.NFT;
import com.oopsfirewolf.nfts.service.NFTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nfts")
@CrossOrigin(origins = "*")
public class NFTController {

    @Autowired
    private NFTService nftService;

    @GetMapping
    public List<NFT> getNFTs() {
        return nftService.getAllNFTs();
    }

    @PostMapping("/buy")
    public String buy(@RequestBody BuyRequest request, HttpServletRequest req) {
        return nftService.handleBuy(request, req);
    }
}
