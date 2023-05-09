package com.project.epharmacy.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public enum EnumAvavilableStatus {

    ACTIVE(1),
    DEACTIVE(0);

    public int value;
}
