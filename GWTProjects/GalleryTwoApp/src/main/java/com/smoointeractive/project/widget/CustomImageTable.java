package com.smoointeractive.project.widget;

import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
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

    private static CustomImageTableUiBinder customImageTableUiBinder = GWT.create(CustomImageTableUiBinder.class);

//    private ResourceBundle resources = GWT.create(ResourceBundle.class);
    private static List<Contact> CONTACTS = Arrays.asList(new Contact("Michael"),
            new Contact("Jane"),
            new Contact("Kim"),
            new Contact("Richard"));

    @UiConstructor
    public CustomImageTable()
    {
        initWidget(customImageTableUiBinder.createAndBindUi(this));

        // Create table
        // Cell table instance
        CellTable<Contact> table = new CellTable<Contact>();

        // Add text column
        TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact object) {
                return object.name;
            }
        };

        // Add image column
        ImageCell imageCell = new ImageCell() {
            @Override
            public void render(Context context, String value, SafeHtmlBuilder safeHtmlBuilder) {
                super.render(context, value, safeHtmlBuilder);
                String imagePath = "01.jpg";
                safeHtmlBuilder.appendHtmlConstant("<img src = '"+imagePath+"' width = '70px' />");
            }
        };

        Column<Contact, String> imageColumn = new Column<Contact, String>(imageCell) {
            @Override
            public String getValue(Contact object) {
                return "";
            }
        };


        table.addColumn(nameColumn, "Name");
        table.addColumn(imageColumn, "");

        // Set the total row count - good habit
        table.setRowCount(CONTACTS.size(), true);

        // Set data source
        table.setRowData(0, CONTACTS);

        // add table to this widget
        HTMLPanel currentHTMLPanel = (HTMLPanel)this.getWidget();
        currentHTMLPanel.add(table);
    }
}
