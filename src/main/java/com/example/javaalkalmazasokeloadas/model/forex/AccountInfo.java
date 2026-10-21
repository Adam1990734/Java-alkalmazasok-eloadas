package com.example.javaalkalmazasokeloadas.model.forex;

import com.oanda.primitives.AccountUnits;

public class AccountInfo {
    private AccountUnits balance;
    private AccountUnits withdrawLimit;
    private Long openPositionCount;
    private Long openTradeCount;
    private Long pendingOrderCount;

    public AccountInfo() {}

    public AccountInfo(AccountUnits balance, AccountUnits withdrawLimit, Long openPositionCount, Long openTradeCount, Long pendingOrderCount) {
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
        this.openPositionCount = openPositionCount;
        this.openTradeCount = openTradeCount;
        this.pendingOrderCount = pendingOrderCount;
    }

    public AccountUnits getBalance() {
        return balance;
    }

    public void setBalance(AccountUnits balance) {
        this.balance = balance;
    }

    public AccountUnits getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(AccountUnits withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public Long getOpenPositionCount() {
        return openPositionCount;
    }

    public void setOpenPositionCount(Long openPositionCount) {
        this.openPositionCount = openPositionCount;
    }

    public Long getOpenTradeCount() {
        return openTradeCount;
    }

    public void setOpenTradeCount(Long openTradeCount) {
        this.openTradeCount = openTradeCount;
    }

    public Long getPendingOrderCount() {
        return pendingOrderCount;
    }

    public void setPendingOrderCount(Long pendingOrderCount) {
        this.pendingOrderCount = pendingOrderCount;
    }
}
