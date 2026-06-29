package com.dairybilling.api.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Hibernate calls this right before running any SQL query.
        // It reads the current string ("dairy_db", "national_dairy_db", etc.) 
        // and swaps the connection!
        return TenantContext.getCurrentTenant();
    }
}