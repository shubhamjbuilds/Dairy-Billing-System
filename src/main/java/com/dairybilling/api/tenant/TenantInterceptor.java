package com.dairybilling.api.tenant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. Look for the X-Tenant-ID header from Postman
        String tenantId = request.getHeader("X-Tenant-ID");

        // 2. If it exists, drop it into our thread-safe box
        if (tenantId != null && !tenantId.trim().isEmpty()) {
            TenantContext.setCurrentTenant(tenantId);
        } else {
            // Default back to master database if no header is passed
            TenantContext.setCurrentTenant(TenantContext.DEFAULT_TENANT);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 3. CRUCIAL: Clear the context after the request finishes to prevent memory leaks!
        TenantContext.clear();
    }
}