package com.springissuetest.views;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;

class LoginViewIT extends AbstractViewIT {

  @Test
  void shouldSignInAndReturnToHome() {
    signIn("admin", "admin");

    assertThat(page).hasURL(url("/"));
  }

  @Test
  void shouldStayOnLoginWithWrongPassword() {
    open("/signin");
    Locator inputs = page.locator("dwc-login[opened] dwc-field input:visible");
    inputs.nth(0).fill("user");
    inputs.nth(1).fill("wrong");
    inputs.nth(1).press("Enter");

    assertThat(page).hasURL(url("/signin?error"));
  }
}
