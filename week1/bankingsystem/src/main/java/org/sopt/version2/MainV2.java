package org.sopt.version2;

import org.sopt.version2.controller.BankController;

public class MainV2 {

    public static void main(String[] args) {

        BankController bankController = new BankController();

        bankController.start();
    }
}
