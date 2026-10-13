package com.springissuetest.views;

import com.webforj.component.Composite;
import com.webforj.component.login.Login;
import com.webforj.router.annotation.Route;
import com.webforj.router.security.annotation.AnonymousAccess;

@Route("/signin")
@AnonymousAccess
public class LoginView extends Composite<Login> {
  private final Login self = getBoundComponent();

  public LoginView() {
    self.setAction("/signin");
    whenAttached().thenAccept(c -> self.open());
  }
}
