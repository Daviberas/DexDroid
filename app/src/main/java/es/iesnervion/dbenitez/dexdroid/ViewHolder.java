package es.iesnervion.dbenitez.dexdroid;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder
{
    TextView tv;
    ImageView iv;

    public ViewHolder(TextView tv, ImageView iv)
    {
        this.tv = tv;
        this.iv = iv;
    }

    public ViewHolder(TextView tv)
    {
        this.tv = tv;
    }

    public TextView getTv ()
    {
        return this.tv;
    }

    public ImageView getIv()
    {
        return iv;
    }
}
