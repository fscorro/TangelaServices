package com.tangela.services.app.repository.aop;

import com.tangela.services.app.repository.ODBConnection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ODBTransactionWrapper
{
	@Before("execution(* com.tangela.core.repository.orientdb.*RepositoryODB.*(..))")
	public void transactionBefore(JoinPoint joinPoint)
	{
		ODBConnection.openObjectDB().begin();
	}

	@AfterReturning("execution(* com.tangela.core.repository.orientdb.*RepositoryODB.*(..))")
	public void transactionAfter(JoinPoint joinPoint) 
	{
		ODBConnection.openObjectDB().commit();
		ODBConnection.openObjectDB().close();
	}

	@AfterThrowing(pointcut = "execution(* com.tangela.core.repository.orientdb.*RepositoryODB.*(..))", throwing = "error")
	public void transactionThrowing(JoinPoint joinPoint, Throwable error)
	{
		ODBConnection.openObjectDB().rollback();
		ODBConnection.openObjectDB().close();
	}
}