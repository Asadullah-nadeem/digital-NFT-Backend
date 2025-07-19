package com.oopsfirewolf.nfts.repository;

import com.oopsfirewolf.nfts.model.NFT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTRepository extends JpaRepository<NFT, Long> {}
