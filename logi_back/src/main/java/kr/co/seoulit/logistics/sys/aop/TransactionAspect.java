package kr.co.seoulit.logistics.sys.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;
@Aspect
@Configuration
public class TransactionAspect {
    private static final String AOP_TRANSACTION_METHOD_NAME = "*";
    private static final String AOP_TRANSACTION_EXPRESSION = "execution(* kr.co.seoulit..service.*.*(..) ) ";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @SuppressWarnings("deprecation")
    @Bean
    public TransactionInterceptor transactionAdvice(){
        MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(DataAccessException.class)));
        source.setTransactionAttribute(transactionAttribute);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor transactionAdviceAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }
}
