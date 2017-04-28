package com.smoointeractive.project.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;


/**
 * Created by sachamoo on 4/19/17.
 */


public class SimpleGrid extends Composite {

    interface SimpleGridUiBinder extends UiBinder<HTMLPanel, SimpleGrid> {

    }
    private static SimpleGridUiBinder simpleGridUiBinder = GWT.create(SimpleGridUiBinder.class);

//    private ResourceBundle resources = GWT.create(ResourceBundle.class);
    private int rowCount = 0;
    private int spacing = 20;
    private VerticalPanel verticalPanel = new VerticalPanel();
    private ArrayList<ImageGalleryDataModel> dataSource;
    private Button selectedButton;
    private DialogBox imageDialogBox;


    public SimpleGrid()
    {
        setDataSource(new ArrayList<>());

        initWidget(simpleGridUiBinder.createAndBindUi(this));

        InitializeGrid();
    }

    @UiConstructor
    public SimpleGrid(ArrayList<ImageGalleryDataModel> list, int rowCount) {
        setDataSource(list);
        setRowCount(rowCount);
        InitializeGrid();
    }

    private void InitializeGrid() {
        // initialize this widget
        initWidget(simpleGridUiBinder.createAndBindUi(this));

        imageDialogBox = new DialogBox();

        GWT.log("SimpleGrid datasource: " +
                                          ((dataSource!=null)?"valid":"invalid"));
        if(null != dataSource) {
            HorizontalPanel horizontalPanel = new HorizontalPanel();

            for (int i = 0; i < dataSource.size(); i++) {
                if ((i % rowCount) == 0) {
                    horizontalPanel = new HorizontalPanel();
                    horizontalPanel.setSpacing(spacing);
                    verticalPanel.add(horizontalPanel);
                }
//                Button thumbnailButton = new Button("<img src='" +
//                        dataSource.get(i).getThumbnail() +"' width='150'></img>");
                Button thumbnailButton = new Button("<img src='" +
                        dataSource.get(i).getThumbnail() +"'></img>");
                thumbnailButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        selectedButton = (Button)event.getSource();
                        String selectedItemImageUrl = "images/"+dataSource.get(selectedButton.getTabIndex()).getImageurl();
                        com.google.gwt.core.client.GWT.log(selectedItemImageUrl);
                        launchDialogBox(selectedItemImageUrl);
//                        launchDialogBox(selectedButton.getHTML());
                    }
                });
                horizontalPanel.add(thumbnailButton);
            }

            HTMLPanel currentHTMLPanel = (HTMLPanel) this.getWidget();
            currentHTMLPanel.add(verticalPanel);
        }
    }

    public void setDataSource(ArrayList<ImageGalleryDataModel> list)
    {
        dataSource = list;
    }

    public void setRowCount(int value)
    {
        rowCount = value;
    }

    private void launchDialogBox(String html)
    {
        com.google.gwt.core.client.GWT.log(html);
//        com.google.gwt.core.client.GWT.log(html.split("\"")[1].toString());

//        String base64String = html.split("\"")[1];
//        Image image = new Image(base64String);
        Image image = new Image(html);
        image.addLoadHandler(event -> {
            Image img = (Image)event.getSource();
//            img.getElement().setAttribute("maxheight", "200px");
//            img.getElement().setAttribute("maxwidth", "100px");
            double scaleFactor = 0.50; // TODO: 4/28/17 replace hardcoded scalefactor with automatic scaling based on rootpanel offsetHeight
            int newWidth = (int)Math.round(img.getWidth() * scaleFactor);
            int newHeight = (int)Math.round(img.getHeight() * scaleFactor);
            com.google.gwt.core.client.GWT.log(String.valueOf(img.getWidth() + "x" + img.getHeight()));
            com.google.gwt.core.client.GWT.log(String.valueOf(newWidth + "x" + newHeight));
            img.getElement().setAttribute("width", String.valueOf(newWidth));
            img.getElement().setAttribute("height", String.valueOf(newHeight));

            imageDialogBox.center();

        });

        imageDialogBox.setText( "you clicked this image");
        imageDialogBox.setAnimationEnabled(true);
        imageDialogBox.setGlassEnabled(true);
        imageDialogBox.setAutoHideEnabled(true);
        imageDialogBox.setWidget(image);
        imageDialogBox.show();
//        imageDialogBox.setWidget(widget);

//        image.setUrl(base64String);
//        Button btn = new Button("x");
//        btn.addClickHandler(event -> imageDialogBox.hide());

//        VerticalPanel htmlPanel = new VerticalPanel();
//        htmlPanel.add(btn);
//        htmlPanel.add(image);



    }
}
