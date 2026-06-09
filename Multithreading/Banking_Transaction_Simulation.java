/*
Create a Java program to simulate a bank system with a shared account balance.

Task:
Initial account balance = 10,000
Create two threads:
Thread 1 → performs 5 withdrawals (random amounts between 500–2000)
Thread 2 → performs 5 deposits (random amounts between 500–2000)
Requirements:
Both threads should operate on the same account balance
Print updated balance after every transaction
Ensure balance never goes negative
Hint:

Think step-by-step:

Shared resource = balance variable
Use synchronization concept (important idea: avoid race condition)
Each thread modifies same data
Control access carefully
*/
import java.util.Random;

class BankAccount {
    private int balance = 10000;

    // Withdraw method
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(
                Thread.currentThread().getName() +
                " withdrew ₹" + amount +
                " | Balance = ₹" + balance
            );
        } else {
            System.out.println(
                Thread.currentThread().getName() +
                " cannot withdraw ₹" + amount +
                " | Insufficient Balance = ₹" + balance
            );
        }
    }

    // Deposit method
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(
            Thread.currentThread().getName() +
            " deposited ₹" + amount +
            " | Balance = ₹" + balance
        );
    }
}

class WithdrawThread extends Thread {
    BankAccount account;
    Random random = new Random();

    WithdrawThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            int amount = random.nextInt(1501) + 500; // 500–2000
            account.withdraw(amount);
        }
    }
}

class DepositThread extends Thread {
    BankAccount account;
    Random random = new Random();

    DepositThread(BankAccount account) {
        this.account = account;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            int amount = random.nextInt(1501) + 500; // 500–2000
            account.deposit(amount);
        }
    }
}

public class Banking_Transaction_Simulation{
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        WithdrawThread t1 = new WithdrawThread(account);
        DepositThread t2 = new DepositThread(account);

        t1.setName("Withdrawal Thread");
        t2.setName("Deposit Thread");

        t1.start();
        t2.start();
    }
}