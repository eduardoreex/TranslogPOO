package modelo;

public class Carga {
    private double pesokg;
    private String tipo;
    private boolean perigosaOuFragil;
    public Carga(String tipo, double pesokg, boolean perigosaOuFragil) {
        this.perigosaOuFragil = perigosaOuFragil;
        this.tipo = tipo;
        this.pesokg = pesokg;
    }

    public double getPesokg() {
        return pesokg;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPerigosaOuFragil(boolean perigosaOuFragil) {
        this.perigosaOuFragil = perigosaOuFragil;
    }

    public void setPesokg(double pesokg) {
        this.pesokg = pesokg;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isPerigosaOuFragil() {
        return perigosaOuFragil;
    }
    public double getValorBaseKm() {
        if(tipo.equalsIgnoreCase("leve")) {
            return 5.00;
        } else if(tipo.equalsIgnoreCase("media")){
            return 8.00;
        } else if (tipo.equalsIgnoreCase("pesada")) {
            return 12.00;
        } else {
            return 0.0;
        }
    }
}
