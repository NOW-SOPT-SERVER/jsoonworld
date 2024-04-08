package org.sopt.version4;

import org.sopt.version4.controller.BankController;

public class MainV4 {

    public static void main(String[] args) {

        BankController bankController = new BankController();

        bankController.start();
    }
}
