package com.smoointeractive.project.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.resources.ResourceBundle;
import com.smoointeractive.project.shared.Contact;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by sachamoo on 4/19/17.
 */


public class SimpleGrid extends Composite {

    interface SimpleGridUiBinder extends UiBinder<HTMLPanel, SimpleGrid> {

    }
    private static SimpleGridUiBinder simpleGridUiBinder = GWT.create(SimpleGridUiBinder.class);

    private ResourceBundle resources = GWT.create(ResourceBundle.class);
    private int rowCount = 0;
    private int spacing = 20;
    private VerticalPanel verticalPanel = new VerticalPanel();
    private ArrayList<ImageGalleryDataModel> dataSource;


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

                horizontalPanel.add(new Button("<img src='" +
                                                dataSource.get(i).getPhoto() +"' width='150'></img>"));
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
}
