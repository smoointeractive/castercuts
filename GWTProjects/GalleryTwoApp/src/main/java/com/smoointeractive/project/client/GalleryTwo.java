package com.smoointeractive.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import com.smoointeractive.project.widget.Main;

import java.util.ArrayList;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GalleryTwo implements EntryPoint {
  /**+++++++++++
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
    private final DataServiceAsync dataService = GWT.create(DataService.class);
  /**
   * This is the entry point method.
   */

  private ArrayList<ImageGalleryDataModel> imagesList;

  public void onModuleLoad() {
//  PaperButton button = new PaperButton("Hello Click Me!");
//    SimpleGrid simpleGrid = new SimpleGrid();
//    CustomImageTable table = new CustomImageTable();
//
//    RootPanel.get().add(simpleGrid);
//    RootPanel.get().add(table);
    Main main = new Main();

    RootPanel.get().add(main);

    dataService.LoadData(new AsyncCallback<String>() {
      @Override
      public void onFailure(Throwable caught) {
        com.google.gwt.core.client.GWT.log("LoadData: Failure trying to receive data from database");
//        System.out.println("Failure trying to receive data from database");
      }

      @Override
      public void onSuccess(String result) {
//        System.out.println("---------------------- " + result);
        com.google.gwt.core.client.GWT.log("---------------------- " + result);

//        TextBox messageLabel = new TextBox();
//        RootPanel.get().add(messageLabel);
//
//        loadTest(messageLabel);
      }
    });


  }

  private void loadTest(TextBox label)
  {
    // get data
    dataService.GetData(new AsyncCallback<ArrayList<ImageGalleryDataModel>>() {
      @Override
      public void onFailure(Throwable caught) {
        com.google.gwt.core.client.GWT.log("GetData: Failure trying to receive data from database");
      }

      @Override
      public void onSuccess(ArrayList<ImageGalleryDataModel> result) {

        com.google.gwt.core.client.GWT.log("----------enterblock------------");

        imagesList = result;

        // grab image found in the first data object
        ImageGalleryDataModel firstElement = imagesList.get(0);

        Image img = new Image(imagesList.get(0).getPhoto());

        RootPanel.get().add(img);

//          image.setUrl(firstElement.getPhoto());
        // add label to notify the user that the image should have been loaded
        label.setText(firstElement.getName() + " was loaded!!!" + ":  " + firstElement.getPhoto());

        com.google.gwt.core.client.GWT.log("-----------exitblock-----------");
      }
    });
  }

}
