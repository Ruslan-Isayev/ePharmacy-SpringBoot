package com.project.epharmacy.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ConfirmationStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    EXPIRED("Expired");

    public String status;
}
