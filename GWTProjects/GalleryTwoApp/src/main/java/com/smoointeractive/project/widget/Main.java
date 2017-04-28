package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import com.vaadin.polymer.iron.widget.IronPages;
import com.vaadin.polymer.paper.widget.PaperTab;

import java.util.ArrayList;
import java.util.Iterator;

//import com.vaadin.polymer.paper.widget.

/**
 * Created by sachamoo on 4/24/17.
 */
public class Main extends Composite implements HasWidgets{

    private int rowCount = 6;

    interface MainUiBinder extends UiBinder<Widget, Main> {

    }
    private static MainUiBinder mainUiBinder = GWT.create(MainUiBinder.class);

//    private List<String> testList = Arrays.asList("1", "2", "3");
    private ArrayList<ImageGalleryDataModel> imageGalleryData;
    private ArrayList<DummyBookModel> dummyBookModelData;

    @UiField
    HTMLPanel mainPanel;
    @UiField
    SimpleGrid imageGrid;
    @UiField
    BookDisplay dummyBookDisplay;
    @UiField
    IronPages pages;
    @UiField
    PaperTab tab1;
    @UiField
    PaperTab tab2;
    @UiField
    PaperTab tab3;
    @UiField
    PaperTab tab4;

    @UiConstructor
    public Main()
    {
        initWidget(mainUiBinder.createAndBindUi(this));
    }

    public Main(ArrayList<ImageGalleryDataModel> imageGalleryData, ArrayList<DummyBookModel> dummyBookModelData)
    {
        this.imageGalleryData = imageGalleryData;
        this.dummyBookModelData = dummyBookModelData;
        initializeContent();
    }

    private void initializeContent() {
//        ArrayList<ImageGalleryDataModel> imageGalleryData = ;
        GWT.log("EntryPoint imageGalleryData: " + ((imageGalleryData !=null)?"valid":"invalid"));
        initWidget(mainUiBinder.createAndBindUi(this));

        tab1.addClickHandler(event -> pages.selectIndex(0));
        tab2.addClickHandler(event -> pages.selectIndex(1));
        tab3.addClickHandler(event -> pages.selectIndex(2));
        tab4.addClickHandler(event -> pages.selectIndex(3));
    }

    @UiChild
    public void addMyContent(Widget widget) {

        HTMLPanel currentHTMLPanel = (HTMLPanel)this.getWidget();
        currentHTMLPanel.add(widget);
    }


    // Used by UiBinder to instantiate SimpleGrid
    @UiFactory
    public SimpleGrid MakeSimpleGrid()
    {
        return new SimpleGrid(imageGalleryData, rowCount);
    }

    @UiFactory
    public BookDisplay MakeBookDisplay() {return new BookDisplay(dummyBookModelData);}

    @Override
    public void add(Widget w) {
        mainPanel.add(w);
    }

    @Override
    public void clear() {
        mainPanel.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return mainPanel.iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return mainPanel.remove(w);
    }

    public void SetImageGalleryDataSource(ArrayList<ImageGalleryDataModel> data) {
        this.imageGalleryData = data;
    }
    public void SetDummyBookDataSource(ArrayList<DummyBookModel> data) { this.dummyBookModelData = data; }

    // get dimension information when object is loaded
    @Override
    protected void onLoad()
    {
        // Note: use the "RootPanel.get().getOffsetWidth" or "RootPanel.get().getOffsetHeight" to get the parent
        // pixel dimensions. Using getOffsetWidth | getOffsetHeight return 0 value.
        com.google.gwt.core.client.GWT.log(String.valueOf(RootPanel.get().getOffsetWidth()));
        com.google.gwt.core.client.GWT.log(String.valueOf(Window.getClientWidth()));
    }
}
