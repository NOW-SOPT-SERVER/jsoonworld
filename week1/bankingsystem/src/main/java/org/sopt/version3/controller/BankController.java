package org.sopt.version3.controller;

import org.sopt.version3.service.AccountService;
import org.sopt.version3.view.InputView;
import org.sopt.version3.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class BankController {
    private final AccountService accountService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Integer, Runnable> optionHandlers;

    public BankController(AccountService accountService, InputView inputView, OutputView outputView) {
        this.accountService = accountService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.optionHandlers = new HashMap<>();
        initializeOptionHandlers();
    }

    private void initializeOptionHandlers() {
        optionHandlers.put(1, this::processDeposit);
        optionHandlers.put(2, this::processWithdraw);
        optionHandlers.put(3, this::processTransfer);
        optionHandlers.put(4, this::processCheckBalance);
        optionHandlers.put(0, this::exitProgram);
    }

    public void start() {
        boolean running = true;
        while (running) {
            try {
                int option = inputView.getUserOption();
                Runnable action = optionHandlers.getOrDefault(option, this::displayInvalidOption);
                action.run();
                if (option == 0) {
                    running = false;
                }
            } catch (Exception e) {
                outputView.displayMessage("[ERROR] 에러 발생: " + e.getMessage());
            }
        }
    }

    private void processDeposit() {
        String accountNumber = inputView.getAccountNumber();
        Integer amount = inputView.getAmount();
        accountService.deposit(accountNumber, amount);
        outputView.displaySuccessMessage("입금", amount);
    }

    private void processWithdraw() {
        String accountNumber = inputView.getAccountNumber();
        Integer amount = inputView.getAmount();
        accountService.withdraw(accountNumber, amount);
        outputView.displaySuccessMessage("출금", amount);
    }

    private void processTransfer() {
        String fromAccountNumber = inputView.getAccountNumber();
        String toAccountNumber = inputView.getRecipientAccountNumber();
        Integer amount = inputView.getAmount();
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        outputView.displaySuccessMessage("이체", amount);
    }

    private void processCheckBalance() {
        String accountNumber = inputView.getAccountNumber();
        Integer balance = accountService.checkBalance(accountNumber);
        outputView.displayBalance(balance);
    }

    private void exitProgram() {
        outputView.displayProgramExitMessage();
    }

    private void displayInvalidOption() {
        outputView.displayInvalidOptionMessage();
    }
}
