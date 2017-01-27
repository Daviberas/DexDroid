package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.widget.TextView;

public class ViewHolder
{
    TextView tv;

    ViewHolder (TextView tv)
    {
        this.tv = tv;
    }

    public TextView getTv ()
    {
        return this.tv;
    }
}
