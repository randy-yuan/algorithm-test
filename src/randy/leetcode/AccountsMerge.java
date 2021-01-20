package randy.leetcode;

import java.util.*;

public class AccountsMerge {
    class Account {
        Integer id;
        String name;
        Set<String> emails;
        Account(Integer id, String name) {
            this.id = id;
            this.name = name;
            this.emails = new HashSet<>();
        }
        Account(String name) {
            this.name = name;
            this.emails = new HashSet<>();
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> emailMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> emails = accounts.get(i);
            for (int j = 1; j < emails.size(); j++) {
                String em = emails.get(j);
                emailMap.computeIfAbsent(em, k -> new ArrayList<>()).add(i);
            }
        }

        List<Account> accList = new ArrayList<>();
        boolean[] visited = new boolean[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            if (!visited[i]) {
                Account acc = new Account(accounts.get(i).get(0));
                dfs(accounts, i, emailMap, visited, acc);
                accList.add(acc);
            }
        }

        List<List<String>> res = new ArrayList<>();
        accList.forEach(a -> {
            List<String> list = new ArrayList<>(a.emails);
            Collections.sort(list);
            list.add(0, a.name);
            res.add(list);
        });
        return res;
    }

    private void dfs(List<List<String>> accounts, int index, Map<String, List<Integer>> emailMap, boolean[] visited, Account acc) {
        if (visited[index]) return;
        visited[index] = true;
        List<String> list = accounts.get(index);
        for (int i = 1; i < list.size(); i++) {
            acc.emails.add(list.get(i));
            List<Integer> indexList = emailMap.get(list.get(i));
            for (int j: indexList) {
                dfs(accounts, j, emailMap, visited, acc);
            }
        }
    }

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, Account> emailMap = new HashMap<>();
        Map<Integer, Account> idMap = new HashMap<>();
        int id = 0;
        for (List<String> a: accounts) {
            Account b = new Account(id++, a.get(0));
            idMap.put(b.id, b);
            for (int i = 1; i < a.size(); i++) {
                String email = a.get(i);
                Account c = emailMap.get(email);
                if (c != null) {
                    if (c != b) {
                        for (String e : b.emails) {
                            c.emails.add(e);
                            emailMap.put(e, c);
                        }
                        idMap.remove(b.id);
                        b = c;
                    }
                } else {
                    b.emails.add(email);
                    emailMap.put(email, b);
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        idMap.values().forEach(a -> {
            List<String> list = new ArrayList<>(a.emails);
            Collections.sort(list);
            list.add(0, a.name);
            res.add(list);
        });
        return res;
    }

    public static void main(String[] args) {
        AccountsMerge test = new AccountsMerge();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David","David0@m.co","David4@m.co","David3@m.co"));
        accounts.add(Arrays.asList("David","David5@m.co","David5@m.co","David0@m.co"));
        accounts.add(Arrays.asList("David","David1@m.co","David4@m.co","David0@m.co"));
        accounts.add(Arrays.asList("David","David0@m.co","David1@m.co","David3@m.co"));
        accounts.add(Arrays.asList("David","David4@m.co","David1@m.co","David3@m.co"));
        test.accountsMerge(accounts);
    }
}