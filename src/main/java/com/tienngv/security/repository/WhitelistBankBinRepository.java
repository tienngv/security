package com.tienngv.security.repository;

import com.tienngv.security.entity.WhitelistBankBin;
import com.tienngv.security.utils.SoS.BankMasterStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WhitelistBankBinRepository extends JpaRepository<WhitelistBankBin, Long> {

    @Query(value = "select c from WhitelistBankBin c where (c.bankBin like :bankBin or :bankBin is null ) and (lower(c.bankName) like :bankName or :bankName is null )  and (c.status = :status or :status is null )")
    Page<WhitelistBankBin> searchWhitelistBankBin(@Param("bankBin") String bankBin, @Param("bankName") String bankName, @Param("status") BankMasterStatus bankMasterStatus, Pageable pageable);

}