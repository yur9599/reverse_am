package com.example.reverse_am.dto.productDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AdminProductDTO {
    @NotNull
    private Boolean verification;
    @Min(value = 1L)
    @Positive
    @NotNull
    private Long revCoin;

    public AdminProductDTO() {
    }

    public AdminProductDTO(Boolean verification, Long revCoin) {
        this.verification = verification;
        this.revCoin = revCoin;
    }

    public Long getRevCoin() {
        return revCoin;
    }

    public void setRevCoin(Long revCoin) {
        this.revCoin = revCoin;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

}
