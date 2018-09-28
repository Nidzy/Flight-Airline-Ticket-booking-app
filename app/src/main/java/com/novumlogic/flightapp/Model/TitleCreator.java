package com.novumlogic.flightapp.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NOVUMLOGIC-2 on 6/1/2017.
 */

public class TitleCreator {

    static TitleCreator _titleCreator;
    List<ParentItemAirline> _titleParents;

    public TitleCreator(Context context) {
        _titleParents = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ParentItemAirline title = new ParentItemAirline(String.format("Caller #%d", i));
            _titleParents.add(title);
        }
    }

    public static TitleCreator get(Context context) {
        if (_titleCreator == null)
            _titleCreator = new TitleCreator(context);
        return _titleCreator;
    }

    public List<ParentItemAirline> getAll() {
        return _titleParents;
    }
}
