package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));


        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);

        System.out.println("Hello World");


        ServiceLocator.GetInstance().RegisterClass(TestClass.class);
        ServiceLocator.GetInstance().RegisterClass(TestClassB.class);
        ServiceLocator.GetInstance().RegisterClass(TestClassC.class);
//        Class[] parameterTypes = {TestClassC.class};
//        Object[] parameterValues = {new TestClassC()};
//        ServiceLocator.GetInstance().RegisterClass(SimpleClassWithDependency.class, parameterTypes, parameterValues);
        ServiceLocator.GetInstance().RegisterClass(SimpleClassWithDependency.class,
                                                    new Class<?>[]{TestClassC.class, TestClass.class},
                                                    new Object[]{new TestClassC(), new TestClass()});


        TestClass test = (TestClass) ServiceLocator.GetInstance().Locate(TestClass.class);

        int someValue = test.AddAbc();

        TestClassB testB = (TestClassB) ServiceLocator.GetInstance().Locate(TestClassB.class);

        int someValueB = testB.AddAbc();

        TestClassC testC = (TestClassC) ServiceLocator.GetInstance().Locate(TestClassC.class);

        TestClassC testCNew = (TestClassC) ServiceLocator.GetInstance().LocateNewInstance(TestClassC.class);

        SimpleClassWithDependency testSimpleClass = (SimpleClassWithDependency)ServiceLocator.GetInstance().Locate(SimpleClassWithDependency.class);
        System.out.println("-->calculated value: " + testSimpleClass);


        int someValueC = testC.AddAbc();
        int someValueC2 = testC.AddAbc();
        testC.setC(1000);
        int someValueC3 = testC.AddAbc();
        int someValueC4 = testC.AddAbc();
        int someValueC5 = testCNew.AddAbc();
        int instanceWithDependency = testSimpleClass.SomeIntValue();
        int instanceWithAnotherDependency = testSimpleClass.SomeOtherIntValue();

        System.out.println("calculated value: " + someValue);
        System.out.println("calculated value: " + someValueB);
        System.out.println("calculated value: " + someValueC);
        System.out.println("calculated value: " + someValueC2);
        System.out.println("calculated value: " + someValueC3);
        System.out.println("calculated value: " + someValueC4);
        System.out.println("calculated value: " + someValueC5);
        System.out.println("calculated value: " + instanceWithDependency);
        System.out.println("calculated value: " + instanceWithAnotherDependency);
    }
}
