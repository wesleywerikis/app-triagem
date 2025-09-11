package br.com.apptriagem;

import java.util.*;

import br.com.apptriagem.model.CaseData;
import br.com.apptriagem.model.Patient;
import br.com.apptriagem.model.TriageEntry;
import br.com.apptriagem.model.enums.RiskLevel;
import br.com.apptriagem.model.enums.Specialty;
import br.com.apptriagem.service.Backtracking;
import br.com.apptriagem.service.PatientIndex;
import br.com.apptriagem.service.TriageQueue;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final TriageQueue fila = new TriageQueue();
    private static final PatientIndex index = new PatientIndex();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1) Cadastrar");
            System.out.println("2) Atender próximo");
            System.out.println("3) Alocar por especialidade (backtracking)");
            System.out.println("0) Sair");
            System.out.print("Opção: ");
            String op = in.nextLine();
            if ("1".equals(op)) cadastrar();
            else if ("2".equals(op)) atender();
            else if ("3".equals(op)) alocar();
            else if ("0".equals(op)) break;
        }
    }

    static void cadastrar() {
        System.out.print("Nome: "); String nome = in.nextLine();
        System.out.print("CPF: "); String cpf = in.nextLine();
        if (index.contains(cpf)) { System.out.println("CPF já na fila."); return; }
        System.out.print("Telefone: "); String fone = in.nextLine();
        System.out.print("Endereço: "); String end = in.nextLine();
        System.out.print("Sintomas: "); String sintomas = in.nextLine();
        boolean febre = yesNo("Febre? (s/n): ");
        boolean cortes = yesNo("Cortes? (s/n): ");
        boolean ulceras = yesNo("Úlceras? (s/n): ");
        System.out.print("Obs: "); String obs = in.nextLine();

        RiskLevel risco = escolherRisco();
        Specialty esp = escolherEsp();

        Patient p = new Patient(nome, cpf, fone, end);
        CaseData c = new CaseData(sintomas, febre, cortes, ulceras, obs, risco, esp);

        fila.add(new TriageEntry(p, c));
        index.put(cpf, new TriageEntry(p, c));
        System.out.println("Incluído na fila: " + nome + " | " + risco + " | " + esp);
    }

    static void atender() {
        TriageEntry e = fila.poll();
        if (e == null) { System.out.println("Fila vazia."); return; }
        System.out.println("Atendendo: " + e.patient().getName() +
                " | CPF " + e.patient().getCpf() +
                " | " + e.data().getRisk() +
                " | " + e.data().getSpecialty());
        index.remove(e.patient().getCpf());
    }

    static void alocar() {
        Map<Specialty,Integer> cap = new EnumMap<>(Specialty.class);
        for (Specialty s : Specialty.values()) {
            System.out.print("Capacidade " + s + ": ");
            try { cap.put(s, Integer.parseInt(in.nextLine())); } catch (Exception e) { cap.put(s, 0); }
        }
        System.out.print("Quantos CPFs tentar alocar? ");
        int n = Integer.parseInt(in.nextLine());
        List<TriageEntry> pool = new ArrayList<>();
        for (int i=0;i<n;i++) {
            System.out.print("CPF "+(i+1)+": ");
            String cpf = in.nextLine();
            TriageEntry e = index.get(cpf);
            if (e != null) pool.add(e);
        }
        Map<Specialty,List<TriageEntry>> res = Backtracking.allocate(pool, cap);
        if (res == null) System.out.println("Sem solução com essas capacidades.");
        else {
            for (Specialty s : Specialty.values()) {
                System.out.print(s + ": ");
                List<TriageEntry> list = res.get(s);
                if (list.isEmpty()) System.out.println("(vazio)");
                else {
                    for (TriageEntry e : list) System.out.print(e.patient().getName() + " ");
                    System.out.println();
                }
            }
        }
    }

    static boolean yesNo(String q){
        while(true){
            System.out.print(q);
            String s = in.nextLine().trim().toLowerCase();
            if (s.startsWith("s")) return true;
            if (s.startsWith("n")) return false;
        }
    }

    static RiskLevel escolherRisco() {
        for (RiskLevel r : RiskLevel.values())
            System.out.println(r.getPriority() + ") " + r + " - " + r.getLabel());
        while (true) {
            System.out.print("Risco (1..5): ");
            try {
                int v = Integer.parseInt(in.nextLine());
                RiskLevel r = RiskLevel.fromPriority(v);
                if (r != null) return r;
            } catch (Exception ignored){}
        }
    }

    static Specialty escolherEsp() {
        Specialty[] v = Specialty.values();
        for (int i=0;i<v.length;i++) System.out.println(i + ") " + v[i]);
        while (true) {
            System.out.print("Especialidade (0.."+(v.length-1)+"): ");
            try {
                int x = Integer.parseInt(in.nextLine());
                if (x>=0 && x<v.length) return v[x];
            } catch (Exception ignored){}
        }
    }
}
