package org.paddy.streams;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.stream.Collectors.toList;
public class Transactions {
    public Transactions() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction;
        for(int i = 0; i < 100; i++) {
            transaction = new Transaction(i,5);
            transactions.add(transaction);
        }
        List<Integer> transactionsIds =
                transactions.stream()
                        .filter(t -> t.getTransactionType() == Transaction.COMPUTER)
                        .sorted(Comparator.comparing(Transaction::getTransactionValue).reversed())
                        .map(Transaction::getTransactionId)
                        .collect(toList());
        System.out.println("Number of computer transactions: " + transactionsIds.size());
        transactions.stream()
                .filter(trans -> transactionsIds.contains(trans.getTransactionId()))
                .map(Transaction::getTransactionValue)
                .collect(toList())
                .forEach(System.out::println);
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
