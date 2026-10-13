package com.springissuetest.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Admin-only business service. Guarded with method security as defence in depth, independent of
 * which view calls it.
 */
@Service
public class MetricsService {

  @PreAuthorize("hasRole('ADMIN')")
  public String getCurrentMetrics() {
    return "Active sessions: 3";
  }
}
