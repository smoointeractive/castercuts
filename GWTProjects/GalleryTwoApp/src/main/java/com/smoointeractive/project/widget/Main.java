package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.client.DataServiceAsync;
import com.smoointeractive.project.client.GalleryTwoIocContainer;
import com.smoointeractive.project.shared.AvailableDatabases;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import com.vaadin.polymer.iron.widget.IronPages;
import com.vaadin.polymer.paper.widget.PaperDialog;
import com.vaadin.polymer.paper.widget.PaperSpinner;
import com.vaadin.polymer.paper.widget.PaperTab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.vaadin.polymer.paper.widget.

/**
 * Created by sachamoo on 4/24/17.
 */
public class Main extends Composite implements HasWidgets{

    private int columnCount = 6;

    interface MainUiBinder extends UiBinder<Widget, Main> {

    }
    private static MainUiBinder mainUiBinder = GWT.create(MainUiBinder.class);

//    private List<String> testList = Arrays.asList("1", "2", "3");
    private ArrayList<ImageGalleryDataModel> imageGalleryData;
    private ArrayList<DummyBookModel> dummyBookModelData;

    private final DataServiceAsync dataService = (DataServiceAsync) GalleryTwoIocContainer.GetInstance().Resolve("dataservice");
    private Logger logger;
    private static DatabaseConnectionResponse databaseConnectionResponse = DatabaseConnectionResponse.FAILURE;
    private PopupPanel loadingModalPopup;
    private PaperDialog paperDialog;

    @UiField
    HTMLPanel mainPanel;
    @UiField
    IronPages pages;
    @UiField
    PaperTab tab1;
    @UiField
    PaperTab tab2;
    @UiField
    PaperTab tab3;
    @UiField
    PaperTab tab4;

    @UiConstructor
    public Main()
    {
        initWidget(mainUiBinder.createAndBindUi(this));
        initializeEventHandlers();

        logger = (Logger)GalleryTwoIocContainer.GetInstance().Resolve("logger");

    }

    public Main(ArrayList<ImageGalleryDataModel> imageGalleryData, ArrayList<DummyBookModel> dummyBookModelData)
    {
        this.imageGalleryData = imageGalleryData;
        this.dummyBookModelData = dummyBookModelData;
        initializeContent();
    }

    private void initializeContent() {
//        ArrayList<ImageGalleryDataModel> imageGalleryData = ;
        GWT.log("EntryPoint imageGalleryData: " + ((imageGalleryData !=null)?"valid":"invalid"));
        logger.log(Level.INFO, "EntryPoint imageGalleryData: " + ((imageGalleryData !=null)?"valid":"invalid"));
        initWidget(mainUiBinder.createAndBindUi(this));

        initializeEventHandlers();
    }

    private void initializeEventHandlers() {
        tab1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pages.selectIndex(0);
            }
        });
        tab2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pages.selectIndex(1);
                logger.log(Level.INFO,"2nd tab pressed");

                if(DatabaseConnectionResponse.SUCCESS == databaseConnectionResponse &&
                        null == imageGalleryData) {
                    BuildImageGallery();
                }
            }
        });
        tab3.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pages.selectIndex(2);
                logger.log(Level.INFO,"3rd tab pressed");

                if(DatabaseConnectionResponse.SUCCESS == databaseConnectionResponse &&
                        null == dummyBookModelData) {
                    LoadDummyGalleryData();
                }

            }
        });
        tab4.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pages.selectIndex(3);
            }
        });
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
        return new SimpleGrid(imageGalleryData, columnCount);
    }

    @UiFactory
    public BookDisplay MakeBookDisplay() {return new BookDisplay(dummyBookModelData);}

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

    public void SetImageGalleryDataSource(ArrayList<ImageGalleryDataModel> data) {
        this.imageGalleryData = data;
    }
    public void SetDummyBookDataSource(ArrayList<DummyBookModel> data) { this.dummyBookModelData = data; }

    // get dimension information when object is loaded
    @Override
    protected void onLoad()
    {
        // Note: use the "RootPanel.get().getOffsetWidth" or "RootPanel.get().getOffsetHeight" to get the parent
        // pixel dimensions. Using getOffsetWidth | getOffsetHeight return 0 value.
        com.google.gwt.core.client.GWT.log(String.valueOf(RootPanel.get().getOffsetWidth()));
        com.google.gwt.core.client.GWT.log(String.valueOf(Window.getClientWidth()));
        logger.log(Level.INFO, String.valueOf(RootPanel.get().getOffsetWidth()));
        logger.log(Level.INFO, String.valueOf(Window.getClientWidth()));

        LoadImageGalleryData();
    }

    private void LoadImageGalleryData()
    {
        databaseConnectionResponse = DatabaseConnectionResponse.FAILURE;
        ShowLoadingModalWindow();
        dataService.LoadData(AvailableDatabases.GALLERY, new AsyncCallback<DatabaseConnectionResponse>() {
            @Override
            public void onFailure(Throwable caught) {
                com.google.gwt.core.client.GWT.log("Error loading data. Error message: " + caught.getMessage());
                logger.log(Level.SEVERE, "Error loading data. Error message: " + caught.getMessage());
            }

            @Override
            public void onSuccess(DatabaseConnectionResponse result) {
                com.google.gwt.core.client.GWT.log(result.toString());
                logger.log(Level.INFO, result.toString());
                databaseConnectionResponse = result;
                loadingModalPopup.hide();
            }
        });
    }

    private void LoadDummyGalleryData()
    {
        databaseConnectionResponse = DatabaseConnectionResponse.FAILURE;
        ShowLoadingModalWindow();
        dataService.LoadData(AvailableDatabases.DUMMY, new AsyncCallback<DatabaseConnectionResponse>() {
            @Override
            public void onFailure(Throwable caught) {
                logger.log(Level.SEVERE, "Error loading data. Error message: " + caught.getMessage());
            }

            @Override
            public void onSuccess(DatabaseConnectionResponse result) {
                logger.log(Level.INFO, result.toString());
                databaseConnectionResponse = result;

                BuildDummyGallery();
            }
    });
    }

    private void BuildImageGallery() {
        dataService.GetImageGalleryData(new AsyncCallback<ArrayList<ImageGalleryDataModel>>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Error retrieving data. Error message: " + caught.getMessage());
                loadingModalPopup.show();
            }

            @Override
            public void onSuccess(ArrayList<ImageGalleryDataModel> result) {
                SetImageGalleryDataSource(result);
                GWT.log("---------<<<<<<<<<"+ imageGalleryData.size());
                System.out.println("---------<<<<<<<<<"+ imageGalleryData.size());
                logger.log(Level.INFO, "---------<<<<<<<<<"+ imageGalleryData.size());
                RootPanel.get("tab2").add(MakeSimpleGrid());
                loadingModalPopup.hide();
            }
        });
    }

    private void BuildDummyGallery() {
        dataService.GetDummyBookData(new AsyncCallback<ArrayList<DummyBookModel>>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Error retrieving data. Error message: " + caught.getMessage());
                loadingModalPopup.show();
            }

            @Override
            public void onSuccess(ArrayList<DummyBookModel> result) {
                SetDummyBookDataSource(result);
                logger.log(Level.INFO, "---------<<<<<<<<<"+ dummyBookModelData.size());
                RootPanel.get("tab3").add(MakeBookDisplay());
                loadingModalPopup.hide();
            }
        });
    }

    private void ShowLoadingModalWindow()
    {
        loadingModalPopup = new PopupPanel();
        loadingModalPopup.setGlassEnabled(true);
        loadingModalPopup.center();
        PaperSpinner spinner = new PaperSpinner();
        spinner.setActive(true);
        loadingModalPopup.add(spinner);
//        loadingModalPopup.add(new Label("Loading..."));
        loadingModalPopup.show();
    }
}
