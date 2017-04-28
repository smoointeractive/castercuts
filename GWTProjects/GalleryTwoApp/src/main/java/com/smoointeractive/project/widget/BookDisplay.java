package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.vaadin.polymer.iron.widget.IronPages;
import com.vaadin.polymer.paper.widget.PaperCard;
import com.vaadin.polymer.paper.widget.PaperIconButton;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sachamoo on 4/26/17.
 */
public class BookDisplay extends Composite {
    interface TestUiBinder extends UiBinder<Widget, BookDisplay> {

    }

    private static TestUiBinder testUiBinder = GWT.create(TestUiBinder.class);

    @UiField
    PaperCard book;
    @UiField
    PaperIconButton backButton;
    @UiField
    PaperIconButton forwardButton;
    @UiField
    RichTextArea textArea;

    ImageLoader imageLoader;
    ArrayList<String> testList = new ArrayList<>(Arrays.asList("01.jpg", "01.jpg", "02.jpg", "01.jpg"));


    public BookDisplay()
    {
        initWidget(testUiBinder.createAndBindUi(this));


    }

    @Override
    protected void onLoad()
    {
        // Note: use the "RootPanel.get().getOffsetWidth" or "RootPanel.get().getOffsetHeight" to get the parent
        // pixel dimensions. Using getOffsetWidth | getOffsetHeight return 0 value.
        int panelWidth = RootPanel.get().getOffsetWidth(); //(int)Math.round(Window.getClientWidth() * 0.98);
        int panelHeight = (int)Math.round(Window.getClientHeight() * 0.70);

        com.google.gwt.core.client.GWT.log(String.valueOf(getParent().getElement().getClientWidth()));

        book.setPixelSize(panelWidth, panelHeight);
        textArea.setPixelSize(30, 30);

        imageLoader = new ImageLoader(book);
        imageLoader.setDatasource(testList);
        imageLoader.setTextArea(textArea);

        // add click events to navigation buttons
        backButton.addClickHandler(event -> imageLoader.previous());
        forwardButton.addClickHandler(event -> imageLoader.next());


        imageLoader.selectItem(0);

//        book.setImage("01.jpg");
    }
}