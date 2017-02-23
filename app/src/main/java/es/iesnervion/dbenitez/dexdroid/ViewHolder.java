package es.iesnervion.dbenitez.dexdroid;

import android.widget.TextView;

public class ViewHolder
{
    TextView tv;

    public ViewHolder(TextView tv)
    {
        this.tv = tv;
    }

    public TextView getTv ()
    {
        return this.tv;
    }
}
