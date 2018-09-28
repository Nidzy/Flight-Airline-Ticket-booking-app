package com.novumlogic.flightapp.Model;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

/**
 * Created by NOVUMLOGIC-2 on 6/1/2017.
 */

public class ParentItemAirline implements ParentObject {


    private List<Object> mChildrenList;
    private UUID _id;
    private String title;

    public ParentItemAirline(String title) {
        this.title = title;
        _id = UUID.randomUUID();
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}
