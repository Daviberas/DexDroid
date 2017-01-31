package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Evolucion implements Parcelable
{
    private int idPreev;
    private int idEv;
    private String metodo;

    public Evolucion(int idPreev,int idEv,String metodo)
    {
        this.idPreev = idPreev;
        this.idEv = idEv;
        this.metodo = metodo;
    }

    protected Evolucion(Parcel in) {
        idPreev = in.readInt();
        idEv = in.readInt();
        metodo = in.readString();
    }

    public static final Creator<Evolucion> CREATOR = new Creator<Evolucion>() {
        @Override
        public Evolucion createFromParcel(Parcel in) {
            return new Evolucion(in);
        }

        @Override
        public Evolucion[] newArray(int size) {
            return new Evolucion[size];
        }
    };

    public int getIdPreev()
    {
        return idPreev;
    }

    public void setIdPreev(int idPreev)
    {
        this.idPreev = idPreev;
    }

    public int getidEv()
    {
        return idEv;
    }

    public void setidEv(int idEv)
    {
        this.idEv = idEv;
    }

    public String getMetodo()
    {
        return metodo;
    }

    public void setMetodo(String metodo)
    {
        this.metodo =  metodo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPreev);
        dest.writeInt(idEv);
        dest.writeString(metodo);
    }
}
