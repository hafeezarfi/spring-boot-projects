package com.hafeezarfi.aopdemo.aspect;

import com.hafeezarfi.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* com.hafeezarfi.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint,Throwable theExc){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: "+method);

        // log the exception
        System.out.println("\n=====>>> The exception is: "+theExc);
    }

    @AfterReturning(
            pointcut = "execution(* com.hafeezarfi.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: "+method);

        // print out the results of method call
        System.out.println("\n=====>>> result is: "+result);

        // let's post-process the data ... let's modify it :-)

        // convert the account name to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> result is: "+result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for(Account tempAccount:result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }

    @Before("com.hafeezarfi.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint){
        System.out.println("\n=====>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();

        System.out.println("Method: "+methodSignature);

        // display method arguments

        // get args
        Object[] args = theJointPoint.getArgs();

        // loop thru args
        for(Object tempArg: args){
            System.out.println(tempArg);

            if (tempArg instanceof Account){

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;
                System.out.println("account name: "+theAccount.getName());
                System.out.println("account level: "+theAccount.getLevel());
            }
        }

    }

}
