//package com.tienngv.security.service;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.tienngv.security.kafka.WhitelistKafkaProducer;
//import com.tienngv.security.request.WhitelistBankBinExcelDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//public class WhitelistBankBinKafkaListener extends AnalysisEventListener<WhitelistBankBinExcelDTO> {
//
//    private final WhitelistKafkaProducer kafkaProducer;
//
//
//    @Override
//    public void invoke(WhitelistBankBinExcelDTO dto, AnalysisContext context) {
//        if ("VISA".equalsIgnoreCase(dto.getBrand()) || "MASTERCARD".equalsIgnoreCase(dto.getBrand())) {
//            kafkaProducer.send(dto);
//        }
//    }
//
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//        log.info("All rows sent to Kafka");
//    }
//}
