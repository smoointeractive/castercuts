package org.smoointeractive.tutorial.client;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.core.client.GWT;

public class HelloWorld {
    interface MyUiBinder extends UiBinder<DivElement, HelloWorld> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField SpanElement nameSpan;

    private DivElement root;

    public HelloWorld() {
        root = uiBinder.createAndBindUi(this);
    }

    public Element getElement() {
        return root;
    }

    public void setName(String name) { nameSpan.setInnerText(name); }
}