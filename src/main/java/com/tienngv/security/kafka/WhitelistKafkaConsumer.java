//package com.tienngv.security.kafka;
//
//import com.tienngv.security.entity.WhitelistBankBin;
//import com.tienngv.security.repository.WhitelistBankBinRepository;
//import com.tienngv.security.request.WhitelistBankBinExcelDTO;
//import com.tienngv.security.utils.SoS.*;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//
//@Log4j2
//@Service
//@RequiredArgsConstructor
//public class WhitelistKafkaConsumer {
//
//    private final WhitelistBankBinRepository repository;
//
//    @KafkaListener(topics = "whitelist-bank-bin-topic", groupId = "whitelist-group", containerFactory = "kafkaListenerContainerFactory")
//    public void listen(WhitelistBankBinExcelDTO dto) {
//        try {
//            WhitelistBankBin entity = new WhitelistBankBin();
//            entity.setBankBin(dto.getBankBin());
//            entity.setBrand(dto.getBrand());
//            entity.setCardType(CreditCardType.valueOf(dto.getCardType()));
//            entity.setCountryCode(dto.getCountryCode());
//            entity.setStatus(BankMasterStatus.ACTIVE);
//            entity.setBankName(dto.getBankName());
//            Timestamp now = new Timestamp(System.currentTimeMillis());
//            entity.setCreatedOn(now);
//            entity.setUpdatedOn(now);
//            repository.save(entity);
//            log.info("Saved entity: {}", dto.getBankBin());
//        } catch (Exception e) {
//            log.error("Lỗi khi xử lý DTO {}: {}", dto, e.getMessage());
//        }
//    }
//}
//
