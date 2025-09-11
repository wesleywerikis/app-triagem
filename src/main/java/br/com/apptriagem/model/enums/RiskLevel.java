package br.com.apptriagem.model.enums;

public enum RiskLevel {
    EMERGENCIA(1, "Emergência"),
    MUITO_URGENTE(2, "Muito urgente"),
    URGENTE(3, "Urgente"),
    POUCO_URGENTE(4, "Pouco urgente"),
    NAO_URGENTE(5, "Não urgente");

    private final int p;
    private final String label;

    RiskLevel(int p, String l) {
        this.p = p;
        this.label = l;
    }

    public int getPriority() {
        return p;
    }

    public String getLabel() {
        return label;
    }

    public static RiskLevel fromPriority(int n) {
        for (RiskLevel r : values())
            if (r.p == n)
                return r;
        return null;
    }
}