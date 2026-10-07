package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        long balance = 0L;
        Scanner sc = new Scanner(System.in);
        System.out.println("     欢迎使用银行账户管理系统");
        System.out.println("\n---------------------");
        while (running){
            System.out.println("1. 存款");
            System.out.println("2. 取款");
            System.out.println("3. 查询余额");
            System.out.println("4. 退出");
            System.out.print("请选择操作 (1-4): ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("请输入存款金额: ");
                    if (sc.hasNextLong()) {
                        long depositAmount = sc.nextLong();
                                balance += depositAmount;
                                System.out.println("存款成功！当前余额: " + balance);
                    }
                    break;
                case 2:
                    System.out.print("请输入取款金额: ");
                    if (sc.hasNextLong()) {
                        long withdrawAmount = sc.nextLong();
                       if (withdrawAmount > balance) {
                            System.out.println("余额不足");
                        } else {
                            balance -= withdrawAmount;
                            System.out.println("取款成功！当前余额: " + balance);
                        }
                    }
                    break;
                case 3:
                    System.out.println("当前余额: " + balance);
                    break;
                case 4:
                    System.out.println("谢谢使用，再见！");
                    running = false;
                    break;
            }
        }
    }
}



