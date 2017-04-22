package com.smoointeractive.project.widget;


import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.resources.ResourceBundle;
import com.smoointeractive.project.shared.Contact;
import com.vaadin.polymer.iron.widget.IronImage;
import com.vaadin.polymer.paper.widget.PaperButton;
import com.vaadin.polymer.paper.widget.PaperInput;
import com.vaadin.polymer.paper.widget.PaperMaterial;
import com.vaadin.polymer.vaadin.widget.VaadinGrid;

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
    private static List<Contact> CONTACTS = Arrays.asList(new Contact("Michael"),
                                                           new Contact("Jane"),
                                                           new Contact("Kim"),
                                                           new Contact("Richard"));
    private int rowCount = 0;
    private VerticalPanel verticalPanel = new VerticalPanel();


    public SimpleGrid(ArrayList<String> list) {
        // initialize this widget
        initWidget(simpleGridUiBinder.createAndBindUi(this));

        HorizontalPanel horizontalPanel = new HorizontalPanel();

        for(int i=0; i < list.size(); i++)
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

    public void setRowCount(int value)
    {
        rowCount = value;
    }
}
