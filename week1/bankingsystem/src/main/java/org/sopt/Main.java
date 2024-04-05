package org.sopt;

import org.sopt.controller.BankController;
import org.sopt.service.AccountService;
import org.sopt.view.input.UserInputView;
import org.sopt.view.output.UserOutputView;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserInputView userInputView = new UserInputView();
        UserOutputView userOutputView = new UserOutputView();

        BankController bankController = new BankController(accountService, userInputView, userOutputView);
        bankController.start();
    }
}
