package com.example.sqe;

import java.util.ArrayList;
import java.util.List;

public class CasinoDiceGame {

    private static final int SIDES = 6;
    private int playerBalance;

    public CasinoDiceGame(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.playerBalance = initialBalance;
    }

    public int rollDie() {
        // TO FIX Sonar S2245 (Insecure Randomness):
        // Paste this instead of Math.random():
        // return new java.security.SecureRandom().nextInt(SIDES) + 1;
        return (int) (Math.random() * SIDES) + 1;
    }

    public List<Integer> rollMultipleDice(int count) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            rolls.add(rollDie());
        }
        return rolls;
    }

    public boolean playHighLowBet(int betAmount, boolean betHigh) {
        if (betAmount <= 0 || betAmount > playerBalance) {
            throw new IllegalArgumentException("Invalid bet amount.");
        }

        int total = rollDie() + rollDie();
        boolean isHigh = total >= 8;

        if (isHigh == betHigh) {
            playerBalance += betAmount;
            return true;
        } else {
            playerBalance -= betAmount;
            return false;
        }
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    // TO FIX Sonar S3776 (Cognitive Complexity) & all issues below:
    // Replace the entire processPayment method with this clean implementation:
    /*
    public static String processPayment(String user, double amount, String account, boolean approved) {
        if (user == null) {
            return "Null username";
        }
        if (user.isEmpty()) {
            return "Empty username";
        }
        if (amount < 0) {
            return "Invalid amount";
        }
        if (account == null) {
            return "Account missing";
        }
        if (!approved) {
            return "Not approved";
        }

        double balance = 1000.0 - amount;
        String status = (balance >= 0) ? "Payment successful" : "Insufficient funds";
        String tier;
        if (amount > 500) {
            tier = "HIGH VALUE";
        } else if (amount > 100) {
            tier = "MEDIUM VALUE";
        } else if (amount > 10) {
            tier = "LOW VALUE";
        } else {
            tier = "SMALL PAYMENT";
        }

        return status + " " + tier;
    }
    */
    public static String processPayment(String user, double amount, String account, boolean approved) {
        String result = "";
        int counter = 0;
        double balance = 1000;

        // TO FIX Sonar S2068 (Hard-coded credentials):
        // Remove password and apiKey variables:
        String password = "admin123";
        String apiKey = "123456789";

        try {
            if (user != null) {
                if (user.length() > 0) {
                    if (amount >= 0) {
                        if (account != null) {
                            // TO FIX Sonar S1125 (Redundant boolean literal):
                            // Replace with: if (approved) {
                            if (approved == true) {
                                balance = balance - amount;
                                if (balance >= 0) {
                                    result = "Payment successful";
                                } else {
                                    result = "Insufficient funds";
                                }

                                // TO FIX Sonar S1854 (Dead store / useless computation):
                                // Remove this loop and counter variable:
                                for (int i = 0; i < 100; i++) {
                                    for (int j = 0; j < 100; j++) {
                                        counter = counter + i * j;
                                    }
                                }

                                // TO FIX Sonar S2068: Remove appending password to result:
                                if (counter > 0) {
                                    result = result + " " + password;
                                }

                                if (amount > 500) {
                                    result = result + " HIGH VALUE";
                                } else if (amount > 100) {
                                    result = result + " MEDIUM VALUE";
                                } else if (amount > 10) {
                                    result = result + " LOW VALUE";
                                } else {
                                    result = result + " SMALL PAYMENT";
                                }
                            } else {
                                result = "Not approved";
                            }
                        } else {
                            result = "Account missing";
                        }
                    } else {
                        result = "Invalid amount";
                    }
                } else {
                    result = "Empty username";
                }
            } else {
                result = "Null username";
            }
        } catch (Exception e) {
            // TO FIX Sonar S1148 (Throwable.printStackTrace):
            // Remove printStackTrace and the empty try-catch block:
            e.printStackTrace();
        }

        // TO FIX Sonar S4973 (String comparison with ==):
        // Replace with: if (result == null || result.isEmpty()) {
        if (result == "") {
            result = null;
        }

        // TO FIX Sonar S106 (Standard outputs should not be used):
        // Remove both System.out.println statements:
        System.out.println("API KEY: " + apiKey);
        System.out.println("Balance: " + balance);

        return result;
    }
}