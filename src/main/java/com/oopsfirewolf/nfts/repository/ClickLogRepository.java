package com.oopsfirewolf.nfts.repository;

import com.oopsfirewolf.nfts.model.ClickLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickLogRepository extends JpaRepository<ClickLog, Long> {}
