package com.springissuetest.views;

import com.springissuetest.service.MetricsService;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;
import jakarta.annotation.security.RolesAllowed;

/**
 * The dashboard pattern from the webforJ Spring routing docs: an injected service builds the UI in
 * the constructor. Route and service both require ADMIN, yet non-admins get an error page instead
 * of the access denied page, because webforJ constructs the view before the route security check
 * and the service call is rejected by Spring method security.
 */
@Route("/")
@RolesAllowed("ADMIN")
public class AdminDashboardView extends Composite<Div> {
  private final Div self = getBoundComponent();

  public AdminDashboardView(MetricsService metricsService) {
    Div metricsPanel = new Div();
    metricsPanel.setText(metricsService.getCurrentMetrics());
    self.add(metricsPanel);
  }
}
