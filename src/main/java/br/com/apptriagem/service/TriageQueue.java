package br.com.apptriagem.service;

import java.util.*;

import br.com.apptriagem.model.TriageEntry;

public class TriageQueue {
    private final List<TriageEntry> list = new ArrayList<>();

    public void add(TriageEntry e) {
        int i = 0;
        while (i < list.size()) {
            TriageEntry x = list.get(i);
            int byRisk = Integer.compare(e.data().getRisk().getPriority(), x.data().getRisk().getPriority());
            if (byRisk < 0)
                break;
            if (byRisk == 0 && e.order() < x.order())
                break;
            i++;
        }
        list.add(i, e);
    }

    public TriageEntry poll() {
        return list.isEmpty() ? null : list.remove(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
