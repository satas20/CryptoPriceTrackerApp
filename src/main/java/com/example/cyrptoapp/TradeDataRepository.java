package com.example.cyrptoapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDataRepository extends JpaRepository<TradeData, Long> {

}
