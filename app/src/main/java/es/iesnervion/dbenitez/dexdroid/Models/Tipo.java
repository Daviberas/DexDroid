package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Tipo implements Parcelable
{
    private int id;
    private String nombre;

    public Tipo(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    protected Tipo(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
    }

    public static final Creator<Tipo> CREATOR = new Creator<Tipo>() {
        @Override
        public Tipo createFromParcel(Parcel in) {
            return new Tipo(in);
        }

        @Override
        public Tipo[] newArray(int size) {
            return new Tipo[size];
        }
    };

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
    }
}
