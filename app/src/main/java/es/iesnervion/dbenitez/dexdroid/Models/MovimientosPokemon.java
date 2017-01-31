package es.iesnervion.dbenitez.dexdroid.Models;

public class MovimientosPokemon
{
    private int idMovimiento;
    private int idPokemon;
    private int mt;
    private int nivel;
    private String movHuevo;
    private String tutor;

    public MovimientosPokemon(int idMovimiento,int idPokemon,int mt, int nivel,String movHuevo,String tutor)
    {
        this.idMovimiento = idMovimiento;
        this.idPokemon = idPokemon;
        this.mt = mt;
        this.nivel = nivel;
        this.movHuevo = movHuevo;
        this.tutor = tutor;
    }

    public int getIdMovimiento()
    {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento)
    {
        this.idMovimiento = idMovimiento;
    }

    public int getIdPokemon()
    {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon)
    {
        this.idPokemon = idPokemon;
    }

    public int getMt()
    {
        return mt;
    }

    public void setMt(int mt)
    {
        this.mt = mt;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    public String getMovHuevo()
    {
        return movHuevo;
    }

    public void setMovHuevo(String movHuevo)
    {
        this.movHuevo = movHuevo;
    }

    public String getTutor()
    {
        return tutor;
    }

    public void setTutor(String tutor)
    {
        this.tutor = tutor;
    }
}
