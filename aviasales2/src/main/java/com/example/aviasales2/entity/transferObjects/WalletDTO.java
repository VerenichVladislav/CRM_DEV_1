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

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }
}
