package org.sopt;

import org.sopt.controller.BankController;

public class Main {

    public static void main(String[] args) {

        BankController bankController = new BankController();

        bankController.start();
    }
}
