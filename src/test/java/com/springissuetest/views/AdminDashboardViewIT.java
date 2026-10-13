package com.springissuetest.views;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * The denial tests fail while webforJ constructs views before the route security check: the
 * constructor's call to the method-secured service throws, so the app answers with a 500 error
 * page instead of redirecting.
 */
class AdminDashboardViewIT extends AbstractViewIT {

  @Test
  void shouldShowMetricsToAdmin() {
    signIn("admin", "admin");
    open("/");

    assertThat(page.getByText("Active sessions: 3")).isVisible();
  }

  @Test
  void shouldDenyUserWithoutAdminRole() {
    signIn("user", "password");
    open("/");

    assertThat(page).hasURL(url("/access-denied?reason=insufficient_roles"));
  }

  @Test
  void shouldRedirectAnonymousToLogin() {
    open("/");

    assertThat(page).hasURL(url("/signin"));
  }
}
