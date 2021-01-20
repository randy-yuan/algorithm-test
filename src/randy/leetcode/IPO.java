package randy.leetcode;

import java.util.*;

public class IPO {
    class Project {
        int profit;
        int capital;

        public Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }

        public int getProfit() {
            return profit;
        }

        public int getCapital() {
            return capital;
        }
    }

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (k < 1) return W;
        if (Profits.length == 0 || Capital.length == 0) return W;

        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < Profits.length; i++) {
            projects.add(new Project(Profits[i], Capital[i]));
        }

        projects.sort(Comparator.comparingInt(Project::getProfit).reversed());

        for (int i = 0; i < k; i++) {
            int j;
            int size = projects.size();
            for (j = 0; j < size; j++) {
                Project p = projects.get(j);
                if (W >= p.capital) {
                    W += p.profit;
                    projects.remove(j);
                    break;
                }
            }
        }

        return W;
    }

    public int findMaximizedCapital2(int k, int W, int[] Profits, int[] Capital) {
        if (k < 1) return W;
        if (Profits.length == 0 || Capital.length == 0) return W;

        Project[] projects = new Project[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            projects[i] = new Project(Profits[i], Capital[i]);
        }

        Arrays.sort(projects, Comparator.comparingInt(Project::getCapital));

        int index = 0;
        PriorityQueue<Project> queue = new PriorityQueue<>(Comparator.comparingInt(Project::getProfit).reversed());
        for (int i = 0; i < k; i++) {
            int j = index;
            for (; j < projects.length && projects[j].capital <= W; j++) {
                queue.offer(projects[j]);
            }
            index = j;
            Project p = queue.poll();
            if (p != null) {
                W += p.profit;
            }
        }

        return W;
    }

    public int findMaximizedCapital3(int k, int W, int[] Profits, int[] Capital) {
        if (k < 1) return W;
        if (Profits.length == 0 || Capital.length == 0) return W;

        Project[] projects = new Project[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            projects[i] = new Project(Profits[i], Capital[i]);
        }

        Arrays.sort(projects, Comparator.comparingInt(Project::getCapital));

        int[] c = new int[k];

        int index = 0;
        PriorityQueue<Project> queue = new PriorityQueue<>(Comparator.comparingInt(Project::getProfit).reversed());
        for (int i = 0; i < k; i++) {
            int j = index;
            for (; j < projects.length && projects[j].capital <= W; j++) {
                queue.offer(projects[j]);
            }
            index = j;
            Project p = queue.poll();
            if (p != null) {
                W += p.profit;
            }
        }

        return W;
    }

    public static void main(String[] args) {
        IPO ipo = new IPO();
        System.out.println(ipo.findMaximizedCapital(1, 0, new int []{1,2,3}, new int[]{0,1,2}));
    }
}
