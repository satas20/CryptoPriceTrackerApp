package com.example.cyrptoapp.model;

import com.example.cyrptoapp.entity.BTCData;
import com.example.cyrptoapp.entity.ETHData;
import com.example.cyrptoapp.entity.SOLData;
import com.example.cyrptoapp.entity.TradeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDataRepository extends JpaRepository<TradeData, Long> {
    @Query(value = "SELECT * FROM btcdata ORDER BY id DESC LIMIT 1", nativeQuery = true)
    BTCData findMostRecentFromBTC();

    @Query(value = "SELECT * FROM ethdata ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ETHData findMostRecentFromETH();

    @Query(value = "SELECT * FROM soldata ORDER BY id DESC LIMIT 1", nativeQuery = true)
    SOLData findMostRecentFromSOL();


}
