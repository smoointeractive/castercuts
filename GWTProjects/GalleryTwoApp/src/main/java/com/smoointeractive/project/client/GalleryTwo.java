package com.smoointeractive.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.smoointeractive.project.helper.DataLoader;
import com.smoointeractive.project.helper.ServiceLocator;
import com.smoointeractive.project.widget.Main;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.iron.IronIconElement;
import com.vaadin.polymer.paper.PaperIconItemElement;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GalleryTwo implements EntryPoint {

  private Main main;
  private Logger logger;

  public void onModuleLoad() {
    // We have to load icon image set before application runs
    // importHref expects the following
    //        param1: list
    //        parem2: Function instance
    Polymer.importHref(Arrays.asList(
            PaperIconItemElement.SRC,
            IronIconElement.SRC,
            "iron-icons"),
            new Function() {
              @Override
              public Object call(Object o) {
                // app is executed when all imports succeed
                GalleryTwo.this.initializeApplication();
                return null;
              }
            });
  }

  private void initializeApplication() {
      // register object instances with service locator
      ServiceLocator.GetInstance().Register("logger", Logger.getLogger(""));
      ServiceLocator.GetInstance().Register("dataservice", GWT.create(DataService.class));
      ServiceLocator.GetInstance().Register("dataloader", new DataLoader(
                (DataServiceAsync) ServiceLocator.GetInstance().Resolve("dataservice"),
                (Logger) ServiceLocator.GetInstance().Resolve("logger")
      ));

    logger = (Logger) ServiceLocator.GetInstance().Resolve("logger");
    main = new Main();
    RootPanel.get().add(main);
    logger.log(Level.INFO, "Application entry point, 'Main', has been initialized.");
  }
}
