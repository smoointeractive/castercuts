package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by sachamoo on 4/26/17.
 */
public class BookDisplay extends Composite {
    interface TestUiBinder extends UiBinder<HTMLPanel, BookDisplay> {

    }

    private static TestUiBinder testUiBinder = GWT.create(TestUiBinder.class);

    public BookDisplay()
    {
        initWidget(testUiBinder.createAndBindUi(this));
    }
}
