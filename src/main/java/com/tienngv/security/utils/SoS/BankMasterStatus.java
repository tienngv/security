package com.tienngv.security.utils.SoS;

import lombok.Getter;

@Getter
public enum BankMasterStatus {
    ACTIVE, PAUSE, DISABLE;
    public boolean isActive(){
        return this.equals(ACTIVE);
    }
}
