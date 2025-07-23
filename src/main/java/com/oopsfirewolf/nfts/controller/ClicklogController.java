package com.oopsfirewolf.nfts.controller;

import com.oopsfirewolf.nfts.dto.BuyRequest;
import com.oopsfirewolf.nfts.model.ClickLog;
import com.oopsfirewolf.nfts.service.ClicklogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nfts")
@CrossOrigin(origins = "*")
public class ClicklogController {

    @Autowired
    private ClicklogService ClicklogService;

    @PostMapping("/buy")
    public String buy(@RequestBody BuyRequest request, HttpServletRequest req) {
        return ClicklogService.handleBuy(request, req);
    }
}
