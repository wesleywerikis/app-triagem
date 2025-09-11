package br.com.apptriagem.service;

import java.util.*;

import br.com.apptriagem.model.TriageEntry;
import br.com.apptriagem.model.enums.Specialty;

public class Backtracking {
    public static Map<Specialty, List<TriageEntry>> allocate(List<TriageEntry> pool, Map<Specialty, Integer> cap) {
        Map<Specialty, List<TriageEntry>> res = new EnumMap<>(Specialty.class);
        for (Specialty s : Specialty.values())
            res.put(s, new ArrayList<>());
        return solve(0, pool, cap, res) ? res : null;
    }

    private static boolean solve(int i, List<TriageEntry> pool, Map<Specialty, Integer> cap,
            Map<Specialty, List<TriageEntry>> res) {
        if (i == pool.size())
            return true;
        TriageEntry e = pool.get(i);
        Specialty s = e.data().getSpecialty();
        if (res.get(s).size() < cap.getOrDefault(s, 0)) {
            res.get(s).add(e);
            if (solve(i + 1, pool, cap, res))
                return true;
            res.get(s).remove(res.get(s).size() - 1);
        }
        return false;
    }
}