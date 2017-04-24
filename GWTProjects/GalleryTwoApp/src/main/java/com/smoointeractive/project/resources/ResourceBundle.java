package com.smoointeractive.project.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by sachamoo on 4/24/17.
 */
public interface ResourceBundle extends ClientBundle{

    @ClientBundle.Source("images/02.jpg")
    ImageResource sampleImage();
}
