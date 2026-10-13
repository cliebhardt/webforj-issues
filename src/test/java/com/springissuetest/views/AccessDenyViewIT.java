package com.springissuetest.views;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.Test;

class AccessDenyViewIT extends AbstractViewIT {

  @Test
  void shouldShowMessageToSignedInUser() {
    signIn("user", "password");
    open("/access-denied");

    assertThat(page.getByText("Access denied")).isVisible();
  }
}
