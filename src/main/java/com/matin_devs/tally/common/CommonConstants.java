package com.matin_devs.tally.common;

import java.util.Set;

public class CommonConstants {
    private CommonConstants() { }

    public static final Set<String> INITIAL_TRANSACTION_CATEGORIES = Set.of(
            "FOOD",
            "TRANSPORT",
            "HEALTH",
            "UTILITIES",
            "ENTERTAINMENT",
            "INCOME"
            );
}
