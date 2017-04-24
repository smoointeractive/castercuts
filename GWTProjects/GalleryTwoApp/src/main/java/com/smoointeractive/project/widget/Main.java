package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sachamoo on 4/24/17.
 */
public class Main extends Composite implements HasWidgets{
    interface MainUiBinder extends UiBinder<Widget, Main> {

    }
    private static MainUiBinder mainUiBinder = GWT.create(MainUiBinder.class);

    private List<String> testList = Arrays.asList("1", "2", "3");

    @UiField
    HTMLPanel mainPanel;

    public Main()
    {
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
        return new SimpleGrid(testList);
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
}
