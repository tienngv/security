//package com.tienngv.security.service;
//
//import com.tienngv.security.request.WhitelistBankBinExcelDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class WhitelistKafkaProducer {
//    private final KafkaTemplate<String, WhitelistBankBinExcelDTO> kafkaTemplate;
//
//    public void send(WhitelistBankBinExcelDTO dto) {
//        kafkaTemplate.send("whitelist-bank-bin-topic", dto);
//    }
//}
