package com.smoointeractive.project.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.iron.widget.IronImage;


/**
 * Created by sachamoo on 4/19/17.
 */

public class SimpleGrid extends Composite {
    interface SimpleGridUiBinder extends UiBinder<HTMLPanel, SimpleGrid> {

    }

    private static SimpleGridUiBinder simpleGridUiBinder = GWT.create(SimpleGridUiBinder.class);

    public SimpleGrid() {
        initWidget(simpleGridUiBinder.createAndBindUi(this));
    }
}
