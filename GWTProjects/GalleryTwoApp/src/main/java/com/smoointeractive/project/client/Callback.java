package com.smoointeractive.project.client;

import com.smoointeractive.project.shared.ImageGalleryDataModel;
import com.smoointeractive.project.widget.BookDisplay;
import com.smoointeractive.project.widget.SimpleGrid;
import com.vaadin.polymer.elemental.Function;

import java.util.ArrayList;

/**
 * Created by sachamoo on 6/19/17.
 */
public interface Callback<T> {
    Object buildWidget(ArrayList<T> result);
}

