package org.smoointeractive.tutorial.client;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class HelloWorld extends Composite{
    interface MyUiBinder extends UiBinder<Widget, HelloWorld> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

//    @UiField SpanElement nameSpan;
    @UiField
    ListBox listBox;
    private DivElement root;


    public HelloWorld(String... names)
    {
        // sets listBox
        initWidget(uiBinder.createAndBindUi(this));

        for(String name: names)
        {
            listBox.addItem(name);
        }
    }
//    public HelloWorld(String... names) {
//        root = uiBinder.createAndBindUi(this);
//    }

//    public Element getElement() {
//        return root;
//    }

//    public void setName(String name) { nameSpan.setInnerText(name); }
}