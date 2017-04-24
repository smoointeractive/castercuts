package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sachamoo on 4/24/17.
 */
public class Main extends Composite {
    interface MainUiBinder extends UiBinder<HTMLPanel, Main> {

    }

    public static MainUiBinder mainUiBinder = GWT.create(MainUiBinder.class);

    private ArrayList<String> testList = (ArrayList<String>) Arrays.asList(  "One", "Two", "Three", "Four",
                                                    "Five", "Six", "Seven", "Eight",
                                                    "Nine", "Ten", "Eleven", "Twelve",
                                                    "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                                                    "Seventeen", "Eighteen", "Nineteen", "Twenty");


    public Main()
    {
        initWidget(mainUiBinder.createAndBindUi(this));
    }


    // Used by UiBinder to instantiate SimpleGrid
    @UiFactory
    public SimpleGrid MakeSimpleGrid()
    {
        return new SimpleGrid(testList);
    }
}
