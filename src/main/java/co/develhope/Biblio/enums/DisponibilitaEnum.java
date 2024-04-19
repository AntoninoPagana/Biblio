package co.develhope.Biblio.enums;

public enum DisponibilitaEnum {
    DISPONIBILE("D"),
    PRESTATO("P");

    private String disponibilitaEnum;

    DisponibilitaEnum(String disponibilitaEnum) {
        this.disponibilitaEnum = disponibilitaEnum;
    }

    public String getDisponibilitaEnum() {
        return disponibilitaEnum;
    }

    public void setDisponibilitaEnum(String disponibilitaEnum) {
        this.disponibilitaEnum = disponibilitaEnum;
    }
}
