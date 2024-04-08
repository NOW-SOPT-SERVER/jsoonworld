package org.sopt.version4.controller;

import java.util.HashMap;
import java.util.Map;
import org.sopt.version4.error.ErrorMessage;
import org.sopt.version4.service.AccountService;
import org.sopt.version4.view.InputView;
import org.sopt.version4.view.OutputView;

public class BankController {
    private final AccountService accountService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Integer, Runnable> optionHandlers;

    public BankController() {
        this.accountService = new AccountService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.optionHandlers = new HashMap<>();
        optionHandlers.put(1, this::processDeposit);
        optionHandlers.put(2, this::processWithdraw);
        optionHandlers.put(3, this::processTransfer);
        optionHandlers.put(4, this::processCheckBalance);
        optionHandlers.put(0, this::exitProgram);
    }

    public void start() {
        boolean running = true;
        while (running) {
            int option = inputView.getUserOption();
            Runnable handler = optionHandlers.getOrDefault(option, this::displayInvalidOptionMessage);
            handler.run();
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

    private void exitProgram() {
        outputView.displayProgramExitMessage();
        System.exit(0);
    }

    private void displayInvalidOptionMessage() {
        outputView.displayInvalidOptionMessage();
    }

    private void handleException(IllegalArgumentException e) {
        ErrorMessage errorMessage = ErrorMessage.valueOf(e.getMessage());
        outputView.displayInputErrorMessage(errorMessage);
    }
}
