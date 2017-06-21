package com.smoointeractive.project.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import com.smoointeractive.project.widget.SimpleGrid;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.widget.PaperSpinner;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sachamoo on 6/19/17.
 */
public class DataLoader {
    private DataServiceAsync dataService;
    private Logger logger;
    private PopupPanel loadingPopupPanel;

    public DataLoader(DataServiceAsync dataService, Logger logger)
    {
        this.dataService = dataService;
        this.logger = logger;
    }

    public void LoadGalleryData(final Callback<ImageGalleryDataModel> callback)
    {
        ShowLoadingModalWindow();

        dataService.LoadData(AvailableDatabases.GALLERY, new AsyncCallback<DatabaseConnectionResponse>() {
            @Override
            public void onFailure(Throwable caught) {
                logger.log(Level.SEVERE, "Error retrieving data. Error message: " + caught.getMessage());
                loadingPopupPanel.show();
            }

            @Override
            public void onSuccess(DatabaseConnectionResponse result) {
                loadingPopupPanel.hide();
                logger.log(Level.INFO, "----> results: " + result.name());

                dataService.GetImageGalleryData(new AsyncCallback<ArrayList<ImageGalleryDataModel>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        logger.log(Level.SEVERE, "Error retrieving data. Error message: " + caught.getMessage());
                        loadingPopupPanel.show();
                    }

                    @Override
                    public void onSuccess(ArrayList<ImageGalleryDataModel> result) {

                        // this is the special part!!!
                        logger.log(Level.INFO, result.toString());
                        callback.buildWidget(result);
                    }
                });
            }
        });
    }

    public void LoadDummyData(final Callback<DummyBookModel> callback)
    {
        ShowLoadingModalWindow();

        dataService.LoadData(AvailableDatabases.DUMMY, new AsyncCallback<DatabaseConnectionResponse>() {
            @Override
            public void onFailure(Throwable caught) {
                logger.log(Level.SEVERE, "Error retrieving data. Error message: " + caught.getMessage());
                loadingPopupPanel.show();
            }

            @Override
            public void onSuccess(DatabaseConnectionResponse result) {
                loadingPopupPanel.hide();
                logger.log(Level.INFO, "----> results: " + result.name());

                dataService.GetDummyBookData(new AsyncCallback<ArrayList<DummyBookModel>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        logger.log(Level.SEVERE, "Error retrieving data. Error message: " + caught.getMessage());
                        loadingPopupPanel.show();
                    }

                    @Override
                    public void onSuccess(ArrayList<DummyBookModel> result) {
                        loadingPopupPanel.hide();
                        logger.log(Level.INFO, "----> results: " + result.size());

                        callback.buildWidget(result);
                    }
                });
            }
        });
    }

    private void ShowLoadingModalWindow()
    {
        loadingPopupPanel = new PopupPanel();
        loadingPopupPanel.setGlassEnabled(true);
        loadingPopupPanel.center();
        PaperSpinner spinner = new PaperSpinner();
        spinner.setActive(true);
        loadingPopupPanel.add(spinner);
        loadingPopupPanel.show();
    }
}
