package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDTO {
    private Long walletId;
    private Long sum;
    private long owner;

    public WalletDTO() {
    }
}
