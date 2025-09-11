package br.com.apptriagem.model;

public class TriageEntry {
    private final Patient p;
    private final CaseData c;
    private final long order;
    private static long seq = 0;

    public TriageEntry(Patient p, CaseData c) {
        this.p = p;
        this.c = c;
        this.order = ++seq;
    }

    public Patient patient() {
        return p;
    }

    public CaseData data() {
        return c;
    }

    public long order() {
        return order;
    }
}
