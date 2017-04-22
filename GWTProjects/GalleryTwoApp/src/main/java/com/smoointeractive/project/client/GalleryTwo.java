package com.smoointeractive.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smoointeractive.project.widget.CustomImageTable;
import com.smoointeractive.project.widget.SimpleGrid;
import com.vaadin.polymer.paper.widget.PaperButton;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GalleryTwo implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
//  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  /**
   * This is the entry point method.
   */

  private ArrayList<String> testList = new ArrayList<String>(Arrays.asList(
          "One", "Two", "Three",
          "Four", "Five", "Six",
          "Seven", "Eight", "Nine",
          "Ten", "Eleven", "Twelve",
          "Thirteen", "Fourteen", "Fifteen",
          "Sixteen", "Seventeen", "Eighteen",
          "Nineteen", "Twenty", "TwentyOne",
          "TwentyTwo", "TwentyThree", "TwentyFour",
          "TwentyFive","TwentySix", "TwentySeven",
          "TwentyEight","TwentyNine","Thirty"));

  public void onModuleLoad() {
//   PaperButton button = new PaperButton("Hello Click Me!");
    SimpleGrid simpleGrid = new SimpleGrid(testList);
    CustomImageTable table = new CustomImageTable();

    RootPanel.get().add(simpleGrid);
    RootPanel.get().add(table);
  }
}
