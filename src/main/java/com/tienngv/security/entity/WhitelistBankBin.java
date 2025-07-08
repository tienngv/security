package com.tienngv.security.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tienngv.security.utils.SoS.BankMasterStatus;
import com.tienngv.security.utils.SoS.CreditCardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "whitelist_bin_credit")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WhitelistBankBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "bank_bin")
    private String bankBin;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BankMasterStatus status;
    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CreditCardType cardType;
    @Column(name = "brand")
    private String brand;
    @Column(name = "created_on")
    @CreationTimestamp
    private Timestamp createdOn;
    @Column(name = "updated_on")
    private Timestamp updatedOn;
    @Column(name = "country_code")
    private String countryCode;



}
