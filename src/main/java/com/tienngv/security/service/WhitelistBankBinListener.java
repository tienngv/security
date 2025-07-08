//package com.tienngv.security.service;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.tienngv.security.entity.WhitelistBankBin;
//import com.tienngv.security.repository.WhitelistBankBinRepository;
//import com.tienngv.security.request.WhitelistBankBinExcelDTO;
//import com.tienngv.security.utils.SoS.BankMasterStatus;
//import com.tienngv.security.utils.SoS.CreditCardType;
//import lombok.extern.slf4j.Slf4j;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Slf4j
//public class WhitelistBankBinListener extends AnalysisEventListener<WhitelistBankBinExcelDTO> {
//
//    private final WhitelistBankBinRepository repository;
//    private final List<WhitelistBankBin> buffer = new ArrayList<>();
//
//    public WhitelistBankBinListener(WhitelistBankBinRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void invoke(WhitelistBankBinExcelDTO dto, AnalysisContext context) {
//        try {
//            if("VISA".equals(dto.getBrand()) || "MASTERCARD".equals(dto.getBrand())) {
//                WhitelistBankBin entity = new WhitelistBankBin();
//                entity.setBankBin(dto.getBankBin());
//
//                entity.setStatus(BankMasterStatus.ACTIVE);
//                entity.setCardType(CreditCardType.valueOf(dto.getCardType()));
//                entity.setBrand(dto.getBrand());
//                entity.setCountryCode(dto.getCountryCode());
//
//                entity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
//                entity.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
//                buffer.add(entity);
//                log.warn("Importing whitelist bank bin: {}", dto.getBankBin());
//            }
//
//            if (buffer.size() >= 100) { // lưu theo batch
//                repository.saveAll(buffer);
//                buffer.clear();
//            }
//
//        } catch (Exception e) {
//            System.err.println("Lỗi dòng: " + dto + ", nguyên nhân: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//        if (!buffer.isEmpty()) {
//            repository.saveAll(buffer);
//        }
//    }
//}
//
