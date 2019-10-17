/**
 * @author: Louise ACosta
 */

package com.gn.model;

import javafx.beans.property.SimpleStringProperty;

public class Region {
    private SimpleStringProperty RegionId;
    private SimpleStringProperty RegionName;

    // constructors
    public Region(String regionId, String regionName) {
        this.RegionId = new SimpleStringProperty(regionId);
        this.RegionName = new SimpleStringProperty(regionName);
    }

    // getters and setters
    public String getRegionId() {
        return RegionId.get();
    }

    public SimpleStringProperty regionIdProperty() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        this.RegionId.set(regionId);
    }

    public String getRegionName() {
        return RegionName.get();
    }

    public SimpleStringProperty regionNameProperty() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        this.RegionName.set(regionName);
    }
}
