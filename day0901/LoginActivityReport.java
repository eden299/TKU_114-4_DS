package day0901;

import java.util.*;

public class LoginActivityReport {
    private Map<String, Integer> userLoginCounts = new HashMap<>();
    private Map<String, Set<String>> userIps = new HashMap<>();

    public void recordLogin(String account, String ip) {
        userLoginCounts.put(account, userLoginCounts.getOrDefault(account, 0) + 1);
        userIps.computeIfAbsent(account, k -> new HashSet<>()).add(ip);
    }

    public void printReport(int anomalyLoginThreshold, int anomalyIpThreshold) {
        System.out.println("=== Login Activity Report ===");
        for (String account : userLoginCounts.keySet()) {
            int count = userLoginCounts.get(account);
            int distinctIpCount = userIps.get(account).size();
            System.out.println("Account: " + account + " | Total Logins: " + count + " | Distinct IPs: " + distinctIpCount);
        }

        System.out.println("\n=== Anomaly Report ===");
        for (String account : userLoginCounts.keySet()) {
            int count = userLoginCounts.get(account);
            int distinctIpCount = userIps.get(account).size();

            if (count >= anomalyLoginThreshold || distinctIpCount >= anomalyIpThreshold) {
                System.out.println("FLAGGED ACCOUNT: " + account + 
                                   " (Logins: " + count + ", IPs: " + distinctIpCount + ")");
            }
        }
    }
}
