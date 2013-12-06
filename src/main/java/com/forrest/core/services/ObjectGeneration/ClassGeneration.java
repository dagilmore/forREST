package com.forrest.core.services.ObjectGeneration;

import javassist.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author David Gilmore
 * @date 12/6/13
 */
@Component
public class ClassGeneration {

    public boolean createPojoAndMapper(String name) {

        return true;
    }

    public Class<?> createPojo(String name) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass clazz = pool.makeClass(name);

            CtField field = new CtField(CtClass.intType, "testField", clazz);
            clazz.addField(field);

            CtMethod getter = CtNewMethod.make("public int getField() { return testField; }", clazz);
            clazz.addMethod(getter);

            CtMethod setter = CtNewMethod.make("public void setField(int testField) { this.testField = testField; }", clazz);
            clazz.addMethod(setter);

            pool.insertClassPath(new ClassClassPath(clazz.toClass()));

            Class myClass = Class.forName(name);
            Constructor constructor = myClass.getConstructor();
            Object instanceOfMyClass = constructor.newInstance();
            Class.forName(name).cast(instanceOfMyClass);
            Field testField = myClass.getDeclaredField("testField");
            Method myGetter = myClass.getDeclaredMethod("getField");
            Method mySetter = myClass.getDeclaredMethod("setField", int.class);
            myGetter.setAccessible(true);
            mySetter.setAccessible(true);
            testField.setAccessible(true);

            testField.set(instanceOfMyClass,3);

            return (Class<?>) instanceOfMyClass;

        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean createMapper(String name) {
        ClassPool pool = ClassPool.getDefault();

        return true;
    }

    public boolean addMethods(HashMap<String,String> methods) {

        return true;
    }
}
