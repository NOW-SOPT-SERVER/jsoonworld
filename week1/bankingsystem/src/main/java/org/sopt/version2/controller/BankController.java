package org.sopt.version2.controller;

import org.sopt.version2.error.ErrorMessage;
import org.sopt.version2.service.AccountService;
import org.sopt.version2.view.InputView;
import org.sopt.version2.view.OutputView;

public class BankController {
    private final AccountService accountService;
    private final InputView inputView;
    private final OutputView outputView;

    public BankController() {
        this.accountService = new AccountService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        boolean running = true;
        while (running) {
            int option = inputView.getUserOption();
            switch (option) {
                case 1:
                    processDeposit();
                    break;
                case 2:
                    processWithdraw();
                    break;
                case 3:
                    processTransfer();
                    break;
                case 4:
                    processCheckBalance();
                    break;
                case 0:
                    running = false;
                    outputView.displayProgramExitMessage();
                    break;
                default:
                    outputView.displayInvalidOptionMessage();
                    break;
            }
        }
    }

    private void processDeposit() {
        try {
            outputView.requestAccountNumberMessage();
            String accountNumber = inputView.getAccountNumber();
            Integer amount = inputView.getAmount();
            accountService.deposit(accountNumber, amount);
            outputView.displaySuccessMessage("입금", amount);
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    private void processWithdraw() {
        try {
            outputView.requestAccountNumberMessage();
            String accountNumber = inputView.getAccountNumber();
            Integer amount = inputView.getAmount();
            accountService.withdraw(accountNumber, amount);
            outputView.displaySuccessMessage("출금", amount);
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    private void processTransfer() {
        try {
            outputView.requestAccountNumberMessage();
            String fromAccountNumber = inputView.getAccountNumber();
            outputView.requestRecipientAccountNumberMessage();
            String toAccountNumber = inputView.getAccountNumber();
            Integer amount = inputView.getAmount();
            accountService.transfer(fromAccountNumber, toAccountNumber, amount);
            outputView.displaySuccessMessage("이체", amount);
        } catch (IllegalArgumentException e) {
            handleException(e);
        }
    }

    private void processCheckBalance() {
        outputView.requestAccountNumberMessage();
        String accountNumber = inputView.getAccountNumber();
        Integer balance = accountService.checkBalance(accountNumber);
        outputView.displayBalance(balance);
    }

    private void handleException(IllegalArgumentException e) {
        ErrorMessage errorMessage = ErrorMessage.valueOf(e.getMessage());
        outputView.displayInputErrorMessage(errorMessage);
    }
}
