package com.example.contextmenu;

import java.util.ArrayList;
import java.util.List;

public class CarListItemView {
    private int ViewId;
    private String ViewBrand;
    private String ViewName;
    private String ViewModel;
    private int ImageViewID;

    public CarListItemView(int id, String brand, String name, String model, int imageId) {
//        super();
        ViewId = id;
        ViewBrand = brand;
        ViewName = name;
        ViewModel = model;
        ImageViewID = imageId;
    }


    public int getListId() {
        return ViewId;
    }
    public String getListBrand() { return ViewBrand; }
    public String getListName() { return ViewName; }
    public String getListModel() {
        return ViewModel;
    }
    public int getListImageId() {
        return ImageViewID;
    }


}
