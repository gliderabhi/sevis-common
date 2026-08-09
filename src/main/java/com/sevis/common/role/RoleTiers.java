package com.sevis.common.role;

import java.util.Set;

/**
 * Groups the SEVIS workshop staff hierarchy (owner > GM > manager > service
 * advisor > supervisor > senior technician > technician > helper) into the
 * two tiers that orders-service/attendance-service authorization already
 * distinguishes: whoever administers job cards/technicians/billing
 * ("management") vs whoever is assigned work and clocks in/out ("staff").
 *
 * ADMIN is the cross-dealer SEVIS platform role and is handled separately by
 * callers (it isn't part of either tier here).
 */
public final class RoleTiers {

    private RoleTiers() {}

    private static final Set<String> STAFF_TIER = Set.of(
            "TECHNICIAN", "SENIOR_TECHNICIAN", "HELPER"
    );

    private static final Set<String> MANAGEMENT_TIER = Set.of(
            "DEALER", "OWNER", "GM", "MANAGER", "SERVICE_ADVISOR", "SUPERVISOR"
    );

    public static boolean isStaff(String role) {
        return STAFF_TIER.contains(role);
    }

    public static boolean isManagement(String role) {
        return MANAGEMENT_TIER.contains(role);
    }
}
