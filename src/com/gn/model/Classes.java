/**
 * @author: Louise Acosta
 */

package com.gn.model;

import javafx.beans.property.SimpleStringProperty;

public class Classes {
    private SimpleStringProperty ClassId;
    private SimpleStringProperty ClassName;
    private SimpleStringProperty ClassDesc;

    // constructor

    public Classes(String classId, String className, String classDesc) {
        this.ClassId = new SimpleStringProperty(classId);
        this.ClassName = new SimpleStringProperty(className);
        this.ClassDesc = new SimpleStringProperty(classDesc);
    }


    // getters and setters

    public String getClassId() {
        return ClassId.get();
    }

    public SimpleStringProperty classIdProperty() {
        return ClassId;
    }

    public void setClassId(String classId) {
        this.ClassId.set(classId);
    }

    public String getClassName() {
        return ClassName.get();
    }

    public SimpleStringProperty classNameProperty() {
        return ClassName;
    }

    public void setClassName(String className) {
        this.ClassName.set(className);
    }

    public String getClassDesc() {
        return ClassDesc.get();
    }

    public SimpleStringProperty classDescProperty() {
        return ClassDesc;
    }

    public void setClassDesc(String classDesc) {
        this.ClassDesc.set(classDesc);
    }
}
