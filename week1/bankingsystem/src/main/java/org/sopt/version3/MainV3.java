package org.sopt.version3;

import org.sopt.version3.controller.BankController;
import org.sopt.version3.service.AccountService;
import org.sopt.version3.view.InputView;
import org.sopt.version3.view.OutputView;

import java.util.HashSet;
import java.util.Set;

public class MainV3 {

    public static void main(String[] args) {

        Set<String> validAccountNumbers = new HashSet<>();
        validAccountNumbers.add("123-456-789");
        validAccountNumbers.add("987-654-321");

        AccountService accountService = new AccountService();
        InputView inputView = new InputView(validAccountNumbers);
        OutputView outputView = new OutputView();

        BankController bankController = new BankController(accountService, inputView, outputView);
        bankController.start();
    }
}
