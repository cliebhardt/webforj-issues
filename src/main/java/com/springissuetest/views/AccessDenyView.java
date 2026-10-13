package com.springissuetest.views;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.router.annotation.Route;

@Route("/access-denied")
public class AccessDenyView extends Composite<Div> {

  public AccessDenyView() {
    getBoundComponent().add(new Paragraph("Access denied"));
  }
}
