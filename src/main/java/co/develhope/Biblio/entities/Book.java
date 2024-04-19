package co.develhope.Biblio.entities;

import co.develhope.Biblio.enums.DisponibilitaEnum;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titolo;
    private String autore;
    private Integer annoDiPubblicazione;
    private Integer timesLent = 0;
    @Enumerated
    @Column(nullable = false)
    private DisponibilitaEnum disponibilitaEnum = DisponibilitaEnum.DISPONIBILE;

    public Book(Long id, String titolo, String autore, Integer annoDiPubblicazione, DisponibilitaEnum disponibilitaEnum, Integer timesLent) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.disponibilitaEnum = disponibilitaEnum;
        this.timesLent = timesLent;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Integer getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(Integer annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public DisponibilitaEnum getDisponibilitaEnum() {
        return disponibilitaEnum;
    }

    public void setDisponibilitaEnum(DisponibilitaEnum disponibilitaEnum) {
        this.disponibilitaEnum = disponibilitaEnum;
    }

    public Integer getTimesLent() {
        return timesLent;
    }

    public void setTimesLent(Integer timesLent) {
        this.timesLent = timesLent;
    }

    public void incrementTimesLent() {
        this.timesLent++;
    }
}