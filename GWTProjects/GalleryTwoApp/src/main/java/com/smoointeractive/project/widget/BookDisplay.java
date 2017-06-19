package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.shared.DummyBookModel;
import com.vaadin.polymer.iron.widget.IronImage;
import com.vaadin.polymer.paper.widget.PaperCard;
import com.vaadin.polymer.paper.widget.PaperIconButton;
import com.vaadin.polymer.paper.widget.PaperInput;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sachamoo on 4/26/17.
 */
public class BookDisplay extends Composite implements BookDisplayEventListener {
    interface TestUiBinder extends UiBinder<Widget, BookDisplay> {

    }

    private static TestUiBinder testUiBinder = GWT.create(TestUiBinder.class);

    @UiField
    PaperCard book;
    @UiField
    IronImage imageHolder;
    @UiField
    PaperIconButton previousButton;
    @UiField
    PaperIconButton nextButton;
    @UiField
    PaperInput pageIndicator;

    ImageLoader imageLoader;
    private ArrayList<DummyBookModel> dataSource;


    public BookDisplay()
    {
        setDataSource(new ArrayList<DummyBookModel>());
        initWidget(testUiBinder.createAndBindUi(this));
    }

    @UiConstructor
    public BookDisplay(ArrayList<DummyBookModel> list)
    {
        setDataSource(list);
        initWidget(testUiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad()
    {
        // Note: use the "RootPanel.get().getOffsetWidth" or "RootPanel.get().getOffsetHeight" to get the parent
        // pixel dimensions. Using getOffsetWidth | getOffsetHeight return 0 value.

        // add browser resize event
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                int panelWidth = RootPanel.get().getOffsetWidth(); //(int)Math.round(Window.getClientWidth() * 0.98);
                int panelHeight = (int) Math.round(Window.getClientHeight() * 0.82);

                GWT.log(String.valueOf(BookDisplay.this.getParent().getElement().getClientWidth()));

                book.setPixelSize(panelWidth, panelHeight);
            }
        });

        int panelWidth = RootPanel.get().getOffsetWidth(); //(int)Math.round(Window.getClientWidth() * 0.98);
        int panelHeight = (int)Math.round(Window.getClientHeight() * 0.82);

        GWT.log(String.valueOf(getParent().getElement().getClientWidth()));

        book.setPixelSize(panelWidth, panelHeight);

        imageLoader = new ImageLoader(imageHolder);
        imageLoader.setDatasource(dataSource);
        imageLoader.setPageIndicator(pageIndicator);

        // add click events to navigation buttons
        previousButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                imageLoader.previous();
            }
        });
        nextButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                imageLoader.next();
            }
        });

        // add to bookdisplayevent
        imageLoader.addEventLister(this);


        imageLoader.selectItem(0);
    }

    @Override
    public void NotifyChanges(Object something) {
        String message = (String)something;
        GWT.log(message);
        // set previous and next button states
        switch(message)
        {
            case "previousDisabled":
                previousButton.setDisabled(true);
                nextButton.setDisabled(false);
                previousButton.setStyle("color: #1ae4ff;");
                nextButton.setStyle("color: white;");
                break;
            case "nextDisabled":
                previousButton.setDisabled(false);
                nextButton.setDisabled(true);
                previousButton.setStyle("color: white;");
                nextButton.setStyle("color: #1ae4ff;");
                break;
            case "enableAllButtons":
                previousButton.setDisabled(false);
                nextButton.setDisabled(false);
                previousButton.setStyle("color: white;");
                nextButton.setStyle("color: white;");
                break;
        }
    }


    public void setDataSource(ArrayList<DummyBookModel> data)
    {
        this.dataSource = data;
    }
}
