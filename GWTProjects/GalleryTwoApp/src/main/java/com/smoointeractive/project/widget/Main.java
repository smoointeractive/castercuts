package com.smoointeractive.project.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.smoointeractive.project.client.DataLoader;
import com.smoointeractive.project.client.DataServiceAsync;
import com.smoointeractive.project.client.GalleryTwoIocContainer;
import com.smoointeractive.project.shared.DatabaseConnectionResponse;
import com.smoointeractive.project.shared.DummyBookModel;
import com.smoointeractive.project.shared.ImageGalleryDataModel;
import com.vaadin.polymer.iron.widget.IronPages;
import com.vaadin.polymer.paper.widget.PaperDialog;
import com.vaadin.polymer.paper.widget.PaperTab;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sachamoo on 4/24/17.
 */
public class Main extends Composite implements HasWidgets{

    private int columnCount = 6;

    interface MainUiBinder extends UiBinder<Widget, Main> {

    }
    private static MainUiBinder mainUiBinder = GWT.create(MainUiBinder.class);
    private ArrayList<ImageGalleryDataModel> imageGalleryData;
    private ArrayList<DummyBookModel> dummyBookModelData;

//    private final DataServiceAsync dataService = (DataServiceAsync) GalleryTwoIocContainer.GetInstance().Resolve("dataservice");
    private Logger logger;
    private static DatabaseConnectionResponse databaseConnectionResponse = DatabaseConnectionResponse.FAILURE;
    private PopupPanel loadingModalPopup;
    private PaperDialog paperDialog;

    private DataLoader dataLoader;

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
        dataLoader =(DataLoader)GalleryTwoIocContainer.GetInstance().Resolve("dataloader");
    }

    public Main(ArrayList<ImageGalleryDataModel> imageGalleryData, ArrayList<DummyBookModel> dummyBookModelData)
    {
        this.imageGalleryData = imageGalleryData;
        this.dummyBookModelData = dummyBookModelData;
        initializeContent();
    }

    private void initializeContent() {
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

                if(null == imageGalleryData) {
                    dataLoader.LoadGalleryData(new com.smoointeractive.project.client.Callback<ImageGalleryDataModel>() {
                        @Override
                        public Object buildWidget(ArrayList<ImageGalleryDataModel> result) {

                            SetImageGalleryDataSource(result);
                            RootPanel.get("tab2").add(MakeSimpleGrid());
                            return null;
                        }
                    });
                }
            }
        });
        tab3.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pages.selectIndex(2);
                logger.log(Level.INFO,"3rd tab pressed");

                if(null == dummyBookModelData) {
                    dataLoader.LoadDummyData(new com.smoointeractive.project.client.Callback<DummyBookModel>() {
                        @Override
                        public Object buildWidget(ArrayList<DummyBookModel> result) {
                            SetDummyBookDataSource(result);
                            RootPanel.get("tab3").add(MakeBookDisplay());
                            return null;
                        }
                    });
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
    }
}
