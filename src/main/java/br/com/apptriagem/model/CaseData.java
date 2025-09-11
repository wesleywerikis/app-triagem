package br.com.apptriagem.model;

import br.com.apptriagem.model.enums.RiskLevel;
import br.com.apptriagem.model.enums.Specialty;

public class CaseData {
    private String symptoms, notes;
    private boolean fever, cuts, ulcers;
    private RiskLevel risk;
    private Specialty specialty;

    public CaseData(String symptoms, boolean fever, boolean cuts, boolean ulcers,
            String notes, RiskLevel risk, Specialty specialty) {
        this.symptoms = symptoms;
        this.fever = fever;
        this.cuts = cuts;
        this.ulcers = ulcers;
        this.notes = notes;
        this.risk = risk;
        this.specialty = specialty;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public boolean hasFever() {
        return fever;
    }

    public boolean hasCuts() {
        return cuts;
    }

    public boolean hasUlcers() {
        return ulcers;
    }

    public String getNotes() {
        return notes;
    }

    public RiskLevel getRisk() {
        return risk;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
}