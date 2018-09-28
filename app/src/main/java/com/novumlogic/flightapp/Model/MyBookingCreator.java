package com.novumlogic.flightapp.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NOVUMLOGIC-2 on 6/2/2017.
 */

public class MyBookingCreator {

    static MyBookingCreator _titleCreator;
    List<ParentItemAirline> _titleParents;

    public MyBookingCreator(Context context) {
        _titleParents = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            ParentItemAirline title = new ParentItemAirline(String.format("Caller #%d", i));
            _titleParents.add(title);
        }
    }

    public static MyBookingCreator get(Context context) {
        if (_titleCreator == null)
            _titleCreator = new MyBookingCreator(context);
        return _titleCreator;
    }

    public List<ParentItemAirline> getAll() {
        return _titleParents;
    }
}

