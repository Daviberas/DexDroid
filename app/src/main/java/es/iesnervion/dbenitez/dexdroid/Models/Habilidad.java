package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Habilidad implements Parcelable
{
    private int id;
    private String nombre;
    private String efecto;

    public Habilidad(int id,String nombre,String efecto)
    {
        this.id = id;
        this.nombre = nombre;
        this.efecto = efecto;
    }

    protected Habilidad(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        efecto = in.readString();
    }

    public static final Creator<Habilidad> CREATOR = new Creator<Habilidad>() {
        @Override
        public Habilidad createFromParcel(Parcel in) {
            return new Habilidad(in);
        }

        @Override
        public Habilidad[] newArray(int size) {
            return new Habilidad[size];
        }
    };

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getEfecto()
    {
        return efecto;
    }

    public void setEfecto(String efecto)
    {
        this.efecto = efecto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(efecto);
    }
}
