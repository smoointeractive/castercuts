package com.smoointeractive.project.widget;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.vaadin.polymer.paper.widget.PaperButton;

/**
 * Created by sachamoo on 4/28/17.
 */
public class SimpleGridButton extends PaperButton {
    private int simpleGridItemIndex;

    public SimpleGridButton(String html)
    {
        super(html);
        this.setRaised(true);
        super.getElement().setAttribute("background", "black");
        super.getElement().setAttribute("border", "5px");
//        super.setStyle("gwt-button");
    }

    public int getSimpleGridItemIndex() {
        return simpleGridItemIndex;
    }
    public void setSimpleGridItemIndex(int value) {
        simpleGridItemIndex = value;
    }
}
