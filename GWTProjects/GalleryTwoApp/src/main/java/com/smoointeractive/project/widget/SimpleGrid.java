package com.smoointeractive.project.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.client.CalculateThumbnailSize;
import com.smoointeractive.project.client.GalleryTwoIocContainer;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by sachamoo on 4/19/17.
 */


public class SimpleGrid extends Composite {

    interface SimpleGridUiBinder extends UiBinder<HTMLPanel, SimpleGrid> {

    }
    private static SimpleGridUiBinder simpleGridUiBinder = GWT.create(SimpleGridUiBinder.class);

//    private ResourceBundle resources = GWT.create(ResourceBundle.class);
    private int columnCount = 0;
    private int spacing = 20;
    private VerticalPanel verticalPanel;
    private ArrayList<ImageGalleryDataModel> dataSource;
//    private Simple selectedButton;
    private DialogBox imageDialogBox;


    public SimpleGrid()
    {
        setDataSource(new ArrayList<ImageGalleryDataModel>());

        initWidget(simpleGridUiBinder.createAndBindUi(this));

        InitializeGrid();
    }

    @UiConstructor
    public SimpleGrid(ArrayList<ImageGalleryDataModel> list, int rowCount) {
        setDataSource(list);
        setColumnCount(rowCount);
        InitializeGrid();
    }

    private void InitializeGrid() {
        // initialize this widget
        initWidget(simpleGridUiBinder.createAndBindUi(this));

        verticalPanel = new VerticalPanel();
        imageDialogBox = new DialogBox();

        GWT.log("SimpleGrid datasource: " +
                                          ((dataSource!=null)?"valid":"invalid"));
        if(null != dataSource) {
            HorizontalPanel horizontalPanel = new HorizontalPanel();

            for (int i = 0; i < dataSource.size(); i++) {
                if ((i % columnCount) == 0) {
                    horizontalPanel = new HorizontalPanel();
                    horizontalPanel.setSpacing(spacing);
                    horizontalPanel.getElement().getStyle().setProperty("margin", "auto");
                    verticalPanel.add(horizontalPanel);
                }
                // get the appropriate thumbnail size based on row count and screen size

                int thumbnailWidth = CalculateThumbnailSize.getMaxWidth(columnCount, Window.getClientWidth());
                Logger logger = (Logger)GalleryTwoIocContainer.GetInstance().Resolve("logger");
                logger.log(Level.INFO, String.valueOf(thumbnailWidth));

                SimpleGridButton thumbnailButton = new SimpleGridButton("<img src='" +
                        dataSource.get(i).getThumbnail() +"'></img>");
                thumbnailButton.setSimpleGridItemIndex(i);
                thumbnailButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        SimpleGridButton selectedButton = (SimpleGridButton)event.getSource();
                        String selectedItemImageUrl = "images/gallery/"+dataSource.get(selectedButton.getSimpleGridItemIndex()).getImageurl();
                        com.google.gwt.core.client.GWT.log(selectedItemImageUrl);
                        launchDialogBox(selectedItemImageUrl);
                    }
                });
                horizontalPanel.add(thumbnailButton);
            }

            HTMLPanel currentHTMLPanel = (HTMLPanel)this.getWidget();
            currentHTMLPanel.add(verticalPanel);
        }
    }

    public void setDataSource(ArrayList<ImageGalleryDataModel> list)
    {
        dataSource = list;
    }

    public void setColumnCount(int value)
    {
        columnCount = value;
    }

    private void launchDialogBox(String html)
    {
        com.google.gwt.core.client.GWT.log(html);
        Image image = new Image(html);
        image.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                Image img = (Image) event.getSource();
                double scaleFactor = 0.50; // TODO: 4/28/17 replace hardcoded scalefactor with automatic scaling based on rootpanel offsetHeight
                int newWidth = (int) Math.round(img.getWidth() * scaleFactor);
                int newHeight = (int) Math.round(img.getHeight() * scaleFactor);
                GWT.log(String.valueOf(img.getWidth() + "x" + img.getHeight()));
                GWT.log(String.valueOf(newWidth + "x" + newHeight));
                img.getElement().setAttribute("width", String.valueOf(newWidth));
                img.getElement().setAttribute("height", String.valueOf(newHeight));

                imageDialogBox.center();
            }
        });

        imageDialogBox.setText( "you clicked this image");
        imageDialogBox.setAnimationEnabled(true);
        imageDialogBox.setGlassEnabled(true);
        imageDialogBox.setAutoHideEnabled(true);
        imageDialogBox.setWidget(image);
        imageDialogBox.show();
    }
}
