package com.hafeezarfi.aopdemo.dao;

import com.hafeezarfi.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account theAccount,boolean vipFlag);

    boolean doWork();
}
