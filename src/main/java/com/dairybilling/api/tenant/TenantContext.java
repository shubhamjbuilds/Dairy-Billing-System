package com.dairybilling.api.tenant;

public class TenantContext {

    // ThreadLocal ensures that each concurrent user gets their own separate variable
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    // The default database we use for things like logins and the master Dairy Group list
    public static final String DEFAULT_TENANT = "dairy_db"; 

    public static void setCurrentTenant(String tenant) {
        CURRENT_TENANT.set(tenant);
    }

    public static String getCurrentTenant() {
        String tenant = CURRENT_TENANT.get();
        return tenant != null ? tenant : DEFAULT_TENANT;
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}