//package com.tienngv.security.controller;
//
//import com.tienngv.security.service.WhitelistBankBinService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/gpay")
//@RequiredArgsConstructor
//public class GpayController {
//    private WhitelistBankBinService service;
//
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
//        service.importFromExcel(file);
//        return ResponseEntity.ok("Đang xử lý file bất đồng bộ bằng Kafka...");
//    }
//}
