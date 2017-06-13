package sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by sachamoo on 6/11/17.
 */
public class ServiceLocator {
    // define hashtable to store registered classes
    private HashMap<Class, Object> registeredClassTable;
    // ServiceLocator is a singleton for now
    private static ServiceLocator sInstance = null;

    public ServiceLocator()
    {
        registeredClassTable = new HashMap<Class, Object>();
    }

    public static ServiceLocator GetInstance()
    {
        if(null == sInstance) {
            sInstance = new ServiceLocator();
        }
        return sInstance;
    }

    public void RegisterClass(Class className) {
        Object someClassInstance = null;
        try {
            someClassInstance = className.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        registeredClassTable.put(className, someClassInstance);
    }

    public void RegisterClass(Class className, Class<?>[] parameterTypes, Object[] parameterValue)
    {
        Constructor<?> constructor;
        Object someClassInstance = null;
        try {
            constructor = className.getConstructor(parameterTypes);

            System.out.println("constructor: "+constructor);

            someClassInstance = constructor.newInstance(parameterValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        registeredClassTable.put(className, someClassInstance);
    }

    public Object Locate(Class className)
    {
        return registeredClassTable.get(className);
    }

    public Object LocateNewInstance(Class className)  {

        Object instance = null;

        try {
            instance = registeredClassTable.get(className).getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
}
