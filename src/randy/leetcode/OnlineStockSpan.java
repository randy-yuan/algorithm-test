package randy.leetcode;

// https://leetcode-cn.com/problems/online-stock-span/
// 901. 股票价格跨度

import java.util.ArrayList;
import java.util.List;

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
public class OnlineStockSpan {
    private List<Integer> prices;
    private List<Integer> spans;

    public OnlineStockSpan() {
        prices = new ArrayList<>();
        spans = new ArrayList<>();
    }

    public int next(int price) {
        int cnt = 1;
        int i = prices.size() - 1;
        while (i >= 0 && price >= prices.get(i)) {
            cnt += spans.get(i);
            i -= spans.get(i);
        }
        prices.add(price);
        spans.add(cnt);
        return cnt;
    }
}
