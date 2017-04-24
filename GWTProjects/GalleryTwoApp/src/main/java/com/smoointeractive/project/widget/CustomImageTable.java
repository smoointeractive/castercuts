package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.smoointeractive.project.resources.ResourceBundle;
import com.smoointeractive.project.shared.Contact;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sachamoo on 4/24/17.
 */
public class CustomImageTable extends Composite {
    interface CustomImageTableUiBinder extends UiBinder<HTMLPanel, CustomImageTable> {

    }

    public static CustomImageTableUiBinder customImageTableUiBinder = GWT.create(CustomImageTableUiBinder.class);

    private ResourceBundle resources = GWT.create(ResourceBundle.class);
    private static List<Contact> CONTACTS = Arrays.asList(new Contact("Michael"),
            new Contact("Jane"),
            new Contact("Kim"),
            new Contact("Richard"));


    public CustomImageTable()
    {
        initWidget(customImageTableUiBinder.createAndBindUi(this));

        // Create table
        // Cell table instance
        CellTable<Contact> table = new CellTable<Contact>();
    }
}
