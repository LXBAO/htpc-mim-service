package com.uwdp.module.biz.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 * @data 2023/7/10 16:01
 */
@Aspect
@Configuration
public class TransactionConfig {
    /**
     * 配置方法过期时间，默认-1,永不超时
     */
    private final static int TX_METHOD_TIME_OUT = 10;

    private static final String POITCUT_EXPRESSION = "execution(* com.uwdp.*..service.*.*(..))";

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public TransactionInterceptor txadvice() {
        RuleBasedTransactionAttribute readOnlyRule = new RuleBasedTransactionAttribute();

        readOnlyRule.setReadOnly(true);

        readOnlyRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        RuleBasedTransactionAttribute requireRule = new RuleBasedTransactionAttribute();

        requireRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));

        requireRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /*设置事务失效时间，超过10秒*/
        requireRule.setTimeout(TX_METHOD_TIME_OUT);


        Map<String, TransactionAttribute> nameMap = new HashMap<>();
        //读写事物
        nameMap.put("add*", requireRule);
        nameMap.put("save*", requireRule);
        nameMap.put("insert*", requireRule);
        nameMap.put("update*", requireRule);
        nameMap.put("delete*", requireRule);
        nameMap.put("remove*", requireRule);
        nameMap.put("batch*", requireRule);
        //只读事物
        nameMap.put("get*", readOnlyRule);
        nameMap.put("query*", readOnlyRule);
        nameMap.put("find*", readOnlyRule);
        nameMap.put("select*", readOnlyRule);
        nameMap.put("count*", readOnlyRule);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.setNameMap(nameMap);

        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(platformTransactionManager, source);

        return transactionInterceptor;
    }

    /**
     * 设置切面=切点pointcut+通知TxAdvice
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(POITCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txadvice());
    }

}
