public class GuerreraBrightMoon {
    public int ID, nivelEstrategia;
    public String alias, poderBatalla, ubicacion;
    public GuerreraBrightMoon sig, ant;

    public GuerreraBrightMoon(int ID, String alias, int nivelEstrategia, String poderBatalla, String ubicacion) {
        this.ID = ID;
        this.alias = alias;
        this.nivelEstrategia = nivelEstrategia;
        this.poderBatalla = poderBatalla;
        this.ubicacion = ubicacion;
        this.sig = null;
        this.ant = null;
    }
}
