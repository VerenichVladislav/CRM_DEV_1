package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class WalletDTO {
    private Long walletId;
    private Long sum;
    private long owner;

    public WalletDTO() {
    }
}
