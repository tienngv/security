//package com.tienngv.security.service;
//
//import com.alibaba.excel.EasyExcel;
//
//import com.tienngv.security.repository.WhitelistBankBinRepository;
//import com.tienngv.security.request.WhitelistBankBinExcelDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class WhitelistBankBinService {
//    private final WhitelistBankBinRepository whitelistBankBinRepository;
//
//    public void importFromExcel(MultipartFile file) {
//        try (InputStream is = file.getInputStream()) {
//            EasyExcel.read(is, WhitelistBankBinExcelDTO.class,
//                            new WhitelistBankBinListener(whitelistBankBinRepository))
//                    .sheet()
//                    .doRead();
//        } catch (IOException e) {
//            throw new RuntimeException("Không thể đọc file Excel: " + e.getMessage(), e);
//        }
//    }
//}
