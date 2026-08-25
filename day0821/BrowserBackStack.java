package day0821;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private Deque<String> stack = new ArrayDeque<>();

    public void visit(String url) {
        stack.push(url);
        System.out.println("瀏覽網頁: " + url);
    }

    public String back() {
        if (stack.isEmpty()) {
            System.out.println("無歷史紀錄，無法返回");
            return null;
        }
        String popped = stack.pop();
        System.out.println("返回上一頁: " + popped);
        return popped;
    }

    public String current() {
        if (stack.isEmpty()) {
            System.out.println("目前無開啟網頁");
            return null;
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        browser.current();
        browser.back();

        browser.visit("https://google.com");
        browser.visit("https://github.com");
        System.out.println("當前頁面: " + browser.current());
        browser.back();
        System.out.println("當前頁面: " + browser.current());
    }
}
