package br.com.apptriagem.service;

import java.util.*;

import br.com.apptriagem.model.TriageEntry;

public class PatientIndex {
    static class Node {
        String k;
        TriageEntry v;

        Node(String k, TriageEntry v) {
            this.k = k;
            this.v = v;
        }
    }

    private final List<Node>[] b;

    @SuppressWarnings("unchecked")
    public PatientIndex() {
        b = new List[97];
        for (int i = 0; i < b.length; i++)
            b[i] = new LinkedList<>();
    }

    private int idx(String k) {
        return (k == null ? 0 : k.hashCode() & 0x7fffffff) % b.length;
    }

    public void put(String k, TriageEntry v) {
        var L = b[idx(k)];
        for (var n : L)
            if (Objects.equals(n.k, k)) {
                n.v = v;
                return;
            }
        L.add(new Node(k, v));
    }

    public TriageEntry get(String k) {
        for (var n : b[idx(k)])
            if (Objects.equals(n.k, k))
                return n.v;
        return null;
    }

    public TriageEntry remove(String k) {
        var L = b[idx(k)];
        var it = L.iterator();
        while (it.hasNext()) {
            var n = it.next();
            if (Objects.equals(n.k, k)) {
                it.remove();
                return n.v;
            }
        }
        return null;
    }

    public boolean contains(String k) {
        return get(k) != null;
    }
}
