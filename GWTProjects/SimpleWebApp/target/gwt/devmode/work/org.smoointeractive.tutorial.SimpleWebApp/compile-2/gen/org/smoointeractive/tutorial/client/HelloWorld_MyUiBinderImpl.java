// .ui.xml template last modified: 1490726732000
package org.smoointeractive.tutorial.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class HelloWorld_MyUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, org.smoointeractive.tutorial.client.HelloWorld>, org.smoointeractive.tutorial.client.HelloWorld.MyUiBinder {


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final org.smoointeractive.tutorial.client.HelloWorld owner) {


    return new Widgets(owner).get_f_HorizontalPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final org.smoointeractive.tutorial.client.HelloWorld owner;


    public Widgets(final org.smoointeractive.tutorial.client.HelloWorld owner) {
      this.owner = owner;
    }


    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private org.smoointeractive.tutorial.client.HelloWorld_MyUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private org.smoointeractive.tutorial.client.HelloWorld_MyUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final org.smoointeractive.tutorial.client.HelloWorld_MyUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (org.smoointeractive.tutorial.client.HelloWorld_MyUiBinderImpl_GenBundle) GWT.create(org.smoointeractive.tutorial.client.HelloWorld_MyUiBinderImpl_GenBundle.class);
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for f_HorizontalPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HorizontalPanel get_f_HorizontalPanel1() {
      return build_f_HorizontalPanel1();
    }
    private com.google.gwt.user.client.ui.HorizontalPanel build_f_HorizontalPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.HorizontalPanel f_HorizontalPanel1 = (com.google.gwt.user.client.ui.HorizontalPanel) GWT.create(com.google.gwt.user.client.ui.HorizontalPanel.class);
      // Setup section.
      f_HorizontalPanel1.add(get_f_Label2());
      f_HorizontalPanel1.add(get_f_Label3());
      f_HorizontalPanel1.add(get_listBox());

      return f_HorizontalPanel1;
    }

    /**
     * Getter for f_Label2 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label2() {
      return build_f_Label2();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label2() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label2 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label2.setText("Keep your ducks");

      return f_Label2;
    }

    /**
     * Getter for f_Label3 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label3() {
      return build_f_Label3();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label3() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label3 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label3.setText("in a row");

      return f_Label3;
    }

    /**
     * Getter for listBox called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.ListBox get_listBox() {
      return build_listBox();
    }
    private com.google.gwt.user.client.ui.ListBox build_listBox() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox listBox = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      listBox.setVisibleItemCount(1);

      this.owner.listBox = listBox;

      return listBox;
    }
  }
}
