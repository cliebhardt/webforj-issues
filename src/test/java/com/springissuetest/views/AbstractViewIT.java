package com.springissuetest.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Starts the app on a random port and a fresh browser per test, with helpers to navigate and sign
 * in with the in-memory users from {@code SecurityConfig}.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractViewIT {

  static Playwright playwright = Playwright.create();
  Browser browser;
  Page page;

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    browser = playwright.chromium().launch();
    page = browser.newPage();
  }

  @AfterEach
  void tearDown() {
    if (browser != null) {
      browser.close();
    }
  }

  String url(String path) {
    return "http://localhost:" + port + path;
  }

  void open(String path) {
    page.navigate(url(path));
  }

  void signIn(String username, String password) {
    open("/signin");
    Locator inputs = page.locator("dwc-login[opened] dwc-field input:visible");
    inputs.nth(0).fill(username);
    inputs.nth(1).fill(password);
    inputs.nth(1).press("Enter");
    page.waitForURL(url("/"));
  }
}
