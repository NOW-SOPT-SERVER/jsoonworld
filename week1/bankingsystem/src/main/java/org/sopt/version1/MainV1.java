package org.sopt.version1;

import org.sopt.version1.controller.BankController;
import org.sopt.version1.service.AccountService;
import org.sopt.version1.view.input.UserInputView;
import org.sopt.version1.view.output.UserOutputView;

public class MainV1 {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserInputView userInputView = new UserInputView();
        UserOutputView userOutputView = new UserOutputView();

        BankController bankController = new BankController(accountService, userInputView, userOutputView);

        bankController.start();
    }
}
