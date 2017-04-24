package com.smoointeractive.project.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.resources.ResourceBundle;
import com.smoointeractive.project.shared.Contact;

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

    private int rowCount = 0;
    private VerticalPanel verticalPanel = new VerticalPanel();
    private ArrayList<String> dataSource;

    @UiConstructor
    public SimpleGrid()
    {
        setDataSource(new ArrayList<>());
        initWidget(simpleGridUiBinder.createAndBindUi(this));

        HorizontalPanel horizontalPanel = new HorizontalPanel();

        for(int i=0; i < dataSource.size(); i++)
        {
            if((i % 9) == 0)
            {
                horizontalPanel = new HorizontalPanel();
                horizontalPanel.setSpacing(5);
                verticalPanel.add(horizontalPanel);
            }

            horizontalPanel.add(new Button("<img src='01.jpg' width='100'></img>"));
        }

        HTMLPanel currentHTMLPanel = (HTMLPanel)this.getWidget();
        currentHTMLPanel.add(verticalPanel);
    }


    public SimpleGrid(ArrayList<String> list) {
        setDataSource(list);

        // initialize this widget
        initWidget(simpleGridUiBinder.createAndBindUi(this));

        HorizontalPanel horizontalPanel = new HorizontalPanel();

        for(int i=0; i < dataSource.size(); i++)
        {
            if((i % 9) == 0)
            {
                horizontalPanel = new HorizontalPanel();
                horizontalPanel.setSpacing(5);
                verticalPanel.add(horizontalPanel);
            }

            horizontalPanel.add(new Button("<img src='images/01.jpg' width='100'></img>"));
        }

        HTMLPanel currentHTMLPanel = (HTMLPanel)this.getWidget();
        currentHTMLPanel.add(verticalPanel);
    }

    public void setDataSource(ArrayList<String> list)
    {
        dataSource = list;
    }

    public void setRowCount(int value)
    {
        rowCount = value;
    }
}
