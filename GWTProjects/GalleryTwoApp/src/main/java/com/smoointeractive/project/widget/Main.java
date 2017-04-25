package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.shared.ImageGalleryDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sachamoo on 4/24/17.
 */
public class Main extends Composite implements HasWidgets{

    private int rowCount = 6;

    interface MainUiBinder extends UiBinder<Widget, Main> {

    }
    private static MainUiBinder mainUiBinder = GWT.create(MainUiBinder.class);

//    private List<String> testList = Arrays.asList("1", "2", "3");
    private ArrayList<ImageGalleryDataModel> data;

    @UiField
    HTMLPanel mainPanel;

    @UiConstructor
    public Main()
    {
        initWidget(mainUiBinder.createAndBindUi(this));
    }

    public Main(ArrayList<ImageGalleryDataModel> data)
    {
        this.data = data;
        com.google.gwt.core.client.GWT.log("EntryPoint data: " + ((data!=null)?"valid":"invalid"));
        initWidget(mainUiBinder.createAndBindUi(this));
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
        return new SimpleGrid(data, rowCount);
    }

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

    public void SetDataSource(ArrayList<ImageGalleryDataModel> data) {
        this.data = data;
    }
}
