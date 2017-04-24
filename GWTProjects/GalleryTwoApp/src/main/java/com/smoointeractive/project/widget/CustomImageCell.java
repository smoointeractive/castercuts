package com.smoointeractive.project.widget;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Created by sachamoo on 4/24/17.
 */
public class CustomImageCell extends AbstractCell<ImageCell> {
    interface Templates extends SafeHtmlTemplates {

        @SafeHtmlTemplates.Template("<div style=\"{0}\">{1}</div>")
        SafeHtml cell(SafeStyles styles, SafeHtml value);
    }

    @Override
    public void render(Context context, ImageCell value, SafeHtmlBuilder sb) {

    }
}
