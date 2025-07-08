package com.tienngv.security.request;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhitelistBankBinExcelDTO implements Serializable {

    @ExcelProperty("BIN")
    private String bankBin;

    @ExcelProperty("Brand")
    private String brand;

    @ExcelProperty("Type")
    private String cardType;

    @ExcelProperty("isoCode2")
    private String countryCode;

    @ExcelProperty("Issuer")
    private String bankName;
}
