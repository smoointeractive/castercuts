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

    interface Resources extends com.smoointeractive.project.resources.ResourceBundle {
    }

    Resources resources = GWT.create(Resources.class);

    private static SimpleGridUiBinder simpleGridUiBinder = GWT.create(SimpleGridUiBinder.class);

    private static List<Contact> CONTACTS = Arrays.asList(new Contact("Michael"),
                                                           new Contact("Jane"),
                                                           new Contact("Kim"),
                                                           new Contact("Richard"));

    private int rowCount = 0;

//    @UiField
//    VaadinGrid grid;
//    @UiField PaperMaterial info;
//    @UiField ImageElement img;
//    @UiField SpanElement txt;
//    @UiField PaperInput filter;
    private VerticalPanel verticalPanel = new VerticalPanel();



    public SimpleGrid(ArrayList<String> list) {
        initWidget(simpleGridUiBinder.createAndBindUi(this));

        // create cell table
        CellTable<Contact> table = new CellTable<>();

        // create name column
        TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact contact) {
                return contact.Name;
            }
        };

        Column<Contact, ImageResource> imageColumn = new Column<Contact, ImageResource>(new ImageResourceCell()) {
            @Override
            public ImageResource getValue(Contact object) {
                return resources.getImageResource();
            }
        };



        // add columns
        table.addColumn(nameColumn, "Name");
        table.addColumn(imageColumn, "");

        // set the width of table and set the table to fixed width mode
        table.setWidth("100%", true);

        // set the width of each column
        table.setColumnWidth(nameColumn, 40.0, Style.Unit.PX);
        table.setColumnWidth(imageColumn, 70.0, Style.Unit.PX);

        // Set the total row count. This isn't strictly necessary, but it affects
        // paging calculations, so its good habit to keep the row count up to date.
        table.setRowCount(CONTACTS.size(), true);

        // Push the data into the widget.
        table.setRowData(0, CONTACTS);

        System.out.println("One Hello Four");

//        HorizontalPanel horizontalPanel = new HorizontalPanel();

//        for (String element: testList) {

//            Image image = new Image();
//            image.setUrl("01.jpg");
//            int newWidth = 150;
//            image.setPixelSize(newWidth, image.getHeight()*newWidth/image.getWidth());
//            verticalPanel.add(image);


            // hack to easily adjust image size

//            System.out.printf(element);
//        }


        HorizontalPanel horizontalPanel = new HorizontalPanel();
//        verticalPanel.setSpacing(5);
        for(int i=0; i < list.size(); i++)
        {


//            HTML image = new HTML("<img src='01.jpg' width='70'></img>");



            if((i % 9) == 0)
            {
                horizontalPanel = new HorizontalPanel();
                horizontalPanel.setSpacing(5);
                verticalPanel.add(horizontalPanel);
            }

            horizontalPanel.add(new Button("<img src='01.jpg' width='100'></img>"));
        }



        RootPanel.get().add(verticalPanel);
        RootPanel.get().add(table);
    }

    public void setRowCount(int value)
    {
        rowCount = value;
    }
}
