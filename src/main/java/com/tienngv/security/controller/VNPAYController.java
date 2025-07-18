package com.tienngv.security.controller;

import com.tienngv.security.service.VnptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class VNPAYController {
    @GetMapping("/vnpay-return")
    public Map<String, String> handleVNPayReturn(@RequestParam Map<String, String> allParams) throws Exception {
        // Lấy tất cả các param do VNPAY gửi về
        System.out.println("All VNPAY Params: " + allParams);

        String vnp_SecureHash = allParams.get("vnp_SecureHash");
        // Xóa khỏi map để tính lại hash
        allParams.remove("vnp_SecureHash");
        allParams.remove("vnp_SecureHashType"); // có thể có

        // Sắp xếp và tính lại hash
        List<String> sortedKeys = new ArrayList<>(allParams.keySet());
        Collections.sort(sortedKeys);

        StringBuilder hashData = new StringBuilder();
        for (String key : sortedKeys) {
            String value = allParams.get(key);
            if (value != null && !value.isEmpty()) {
                hashData.append(key).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
            }
        }
        hashData.setLength(hashData.length() - 1); // bỏ & cuối

        String recomputedHash = VnptService.hmacSHA512("R5HFYZSNKQT2OKD936W43TKZPTRZOKGF", hashData.toString());

        Map<String, String> result = new HashMap<>();
        if (recomputedHash.equals(vnp_SecureHash)) {
            // ✅ Hợp lệ, xử lý đơn hàng tại đây
            String responseCode = allParams.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                result.put("RspCode ", "00");
                result.put("Message ", "Confirm Success");
            } else {
                result.put("RspCode ", responseCode);
                result.put("Message ", "Err!!");
            }
        } else {
            result.put("RspCode ", "99");
            result.put("Message ", " invalid_signature");

        }
        return result;
    }


}
