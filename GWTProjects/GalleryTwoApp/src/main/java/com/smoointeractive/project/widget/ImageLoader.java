package com.smoointeractive.project.widget;

import com.google.gwt.user.client.ui.RichTextArea;
import com.smoointeractive.project.shared.DummyBookModel;
import com.vaadin.polymer.iron.widget.IronImage;
import com.vaadin.polymer.paper.widget.PaperCard;

import java.util.ArrayList;

/**
 * Created by sachamoo on 4/27/17.
 */
public class ImageLoader {
    private ArrayList<DummyBookModel> imagePaths;
    private PaperCard paperCard;
    private IronImage ironImage;
    private boolean canNavigate = false;
    private int currentIndex = 0;
    private RichTextArea textArea;
    private static final String pathPrefix = "images/";

    public ImageLoader(PaperCard card)
    {
        paperCard = card;
    }
    public ImageLoader(IronImage image)
    {
        ironImage = image;
    }

    public void setDatasource(ArrayList<DummyBookModel> list)
    {
        this.imagePaths = list;
    }

    public void setTextArea(RichTextArea textArea)
    {
        this.textArea = textArea;
        this.textArea.setText("0");
    }

    public boolean next()
    {
        if(currentIndex < (imagePaths.size()-1)) {
            currentIndex++;
            canNavigate = true;
            iterateThroughData();
        }
        else
        {
            canNavigate = false;
        }
        com.google.gwt.core.client.GWT.log("class: ImageLoader -> next(), currentIndex: " + currentIndex);
        return canNavigate;
    }

    public boolean previous()
    {
        if(currentIndex > 0) {
            currentIndex--;
            canNavigate = true;
            iterateThroughData();
        }
        else
        {
            canNavigate = false;
        }
        com.google.gwt.core.client.GWT.log("class: ImageLoader -> previous(), currentIndex: " + currentIndex);
        return canNavigate;
    }

    public void selectItem(int index)
    {
        if(null !=imagePaths && imagePaths.size() >= 1)
        {
//            paperCard.setImage(imagePaths.get(index).getImageurl());
            ironImage.setSrc(imagePaths.get(index).getImageurl());
            if(null != textArea) textArea.setText(String.valueOf(currentIndex));
        }
    }

    private void iterateThroughData()
    {
        if(null !=imagePaths && imagePaths.size() >= 1)
        {
//            paperCard.getElement().setAttribute("sizing", "cover");
//            paperCard.getElement().setAttribute("sizing", "cover");
            ironImage.setSrc(pathPrefix + imagePaths.get(currentIndex).getImageurl());
//            paperCard.setImage(pathPrefix + imagePaths.get(currentIndex).getImageurl());

            if(null != textArea) textArea.setText(String.valueOf(currentIndex));
        }
    }
}
