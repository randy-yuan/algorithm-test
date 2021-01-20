package randy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BabyNamesLCCI {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> nameFreq = new HashMap<>();
        Map<String, Integer> nameIndex = new HashMap<>();
        List<String[]> symList = new ArrayList<>();
        int index = 0;
        for (String s : names) {
            String[] a = parseNameFreq(s);
            nameFreq.put(a[0], Integer.parseInt(a[1]));
            nameIndex.put(a[0], index++);
        }
        for (String sym: synonyms) {
            String[] a = parseSynonyms(sym);
            symList.add(a);
            if (!nameIndex.containsKey(a[0])) nameIndex.put(a[0], index++);
            if (!nameIndex.containsKey(a[1])) nameIndex.put(a[1], index++);
        }

        UnionFind uf = new UnionFind(nameIndex.size());
        for (String[] a: symList) uf.union(nameIndex.get(a[0]), nameIndex.get(a[1]));

        int[] indexFreq = new int[nameIndex.size()];
        String[] indexName = new String[indexFreq.length];
        nameFreq.forEach((name, freq) -> {
            int root = uf.find(nameIndex.get(name));
            indexFreq[root] += freq;
            if (indexName[root] == null || name.compareTo(indexName[root]) < 0) indexName[root] = name;
        });

        List<String> res = new ArrayList<>();
        for (int i = 0; i < indexFreq.length; i++) {
            if (indexFreq[i] > 0) {
                res.add(indexName[i] + '(' + indexFreq[i] + ')');
            }
        }
        return res.toArray(new String[0]);
    }

    private String[] parseNameFreq(String s) {
        int idx = s.indexOf('(');
        return new String[]{s.substring(0, idx), s.substring(idx + 1, s.length()-1)};
    }

    private String[] parseSynonyms(String s) {
        int idx = s.indexOf(',');
        return new String[]{s.substring(1, idx), s.substring(idx+1, s.length()-1)};
    }

    public static void main(String[] args) {
        BabyNamesLCCI test = new BabyNamesLCCI();
        PrintUtils.print(test.trulyMostPopular(new String[]{"a(10)","c(13)"}, new String[]{"(a,b)","(c,d)","(b,c)"}));
    }
}