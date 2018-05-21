package org.paddy.streams;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
public class Transactions {
    public Transactions() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction;
        for(int i = 0; i < 100; i++) {
            transaction = new Transaction(i,5);
            transactions.add(transaction);
        }
        List<Integer> computerTransactionsIds =
                transactions.stream()
                        .filter(t -> t.getTransactionType() == Transaction.COMPUTER)
                        .sorted(Comparator.comparing(Transaction::getTransactionValue).reversed())
                        .map(Transaction::getTransactionId)
                        .collect(toList());
        String idsS = computerTransactionsIds.stream()
                .map(i -> String.valueOf(i.intValue()))
                .collect(Collectors.joining(", "));
        System.out.println("Ids: " +idsS);
        List<Double> computerTransactionsValues =
                transactions.stream()
                        .filter(t -> t.getTransactionType() == Transaction.COMPUTER)
                        .sorted(Comparator.comparing(Transaction::getTransactionValue).reversed())
                        .map(Transaction::getTransactionValue)
                        .collect(toList());
        System.out.println("Number of computer transactions: " + computerTransactionsValues.size());
        double avrg = computerTransactionsValues.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(Double.NaN);
        computerTransactionsValues.stream()
                .map(value -> "Computer transaction cost: " +
                        String.format(Locale.GERMAN, "%1$,.2f", value) + " €\tdeviation: " +
                        String.format(Locale.GERMAN, "%1$,.2f", value - avrg) + " €")
                .collect(toList())
                .forEach(System.out::println);
        double sum = computerTransactionsValues.stream()
                .mapToDouble(d -> d)
                .sum();
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println("\nSum of all computer transactions:\t\t" + String.format(Locale.GERMAN, "%1$,.4f", sum) + " €");
        System.out.println("Average of all comouter transactions:\t" + String.format(Locale.GERMAN, "%1$,.4f", avrg) + " €");
    }
    private class Transaction {
        static final int GROCERY = 1;
        static final int HOUSEWARE = 2;
        static final int STATIONERY = 3;
        static final int COMPUTER = 4;
        static final int SOFTWARE = 5;
        private static final int min = 0;
        private final double scale = Math.pow(10, 2);
        private int transactionId;
        private int transactionType;
        private double transactionValue;
        private Transaction(int transactionId, int max) {
            this.transactionId = transactionId;
            setTransactionType(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
        int getTransactionId() {
            return transactionId;
        }
        int getTransactionType() {
            return transactionType;
        }
        private void setTransactionType(int transactionType) {
            this.transactionType = transactionType;
            switch (transactionType) {
                case GROCERY:
                    this.transactionValue = Math.round(0.01 + Math.random() * 19.9 * scale) / scale;
                    break;
                case HOUSEWARE:
                    this.transactionValue = Math.round(1.0 + Math.random() * 49.0 * scale) / scale;
                    break;
                case STATIONERY:
                    this.transactionValue = Math.round(5.0 + Math.random() * 95.0 * scale) / scale;
                    break;
                case  COMPUTER:
                    this.transactionValue = Math.round(10 + Math.random() * 9990.0 * scale) / scale;
                    break;
                case SOFTWARE:
                    this.transactionValue = Math.round(0.01 + Math.random() * 999.9 * scale) / scale;
                    break;
                    default:
                        break;
            }

        }
        double getTransactionValue() {
            return transactionValue;
        }
    }
}
