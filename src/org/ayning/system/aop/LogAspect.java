package org.ayning.system.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayning.vo.OperationLog;

/**
 * LogAspect is a log component based on Spring AOP technology. This Object will
 * log Java method invocation event and persist the event into database for
 * logging. But as time limits I just print the log event into a log file
 * instead of database.
 * 
 * @author Alex
 * 
 */
@Component("logAspect")
@Aspect
public class LogAspect {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LogAspect.class);

	@Autowired
	private HttpServletRequest request;

	public LogAspect() {
		LOGGER.debug("LogAspect initialized");
	}

	/**
	 * Spring AOP around advice
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();

	}

	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		try {
			OperationLog log = this.generateLog(joinPoint, e);
			LOGGER.info(log.toString());
		} catch (Exception e1) {
			LOGGER.error("Fail to generate Excetion operate log: "
					+ e1.getMessage());
		}
	}

	public void afterReturn(JoinPoint joinPoint, Object retVal) {
		try {
			OperationLog log = this.generateLog(joinPoint);
			LOGGER.info(log.toString());
		} catch (Exception e) {
			LOGGER.error("Fail to generate common operate log: "
					+ e.getMessage());
		}
	}

	/**
	 * Genrate a common log
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	private OperationLog generateLog(JoinPoint joinPoint) throws Exception {

		OperationLog log = new OperationLog();
		//
		// Admin admin = (Admin)
		// this.request.getSession().getAttribute("admin");
		// log.setIntranetID(admin.getIntranetID());
		// log.setFullName(admin.getFullName());
		// log.setCountry(admin.getCountry());
		// log.setUserIP(request.getRemoteAddr());
		// log.setOperateDate(new Timestamp(new Date().getTime()).toString());
		//
		// MethodSignature signature = (MethodSignature)
		// joinPoint.getSignature();
		// log.setClassName(signature.getMethod().getDeclaringClass().getName());
		// log.setMethod(signature.getName());
		//
		// LogMarker marker = (LogMarker) (signature.getMethod()
		// .getAnnotation(LogMarker.class));
		// log.setMessage(marker.desc());

		return log;
	}

	/**
	 * Generate the OperateLog while Exception happens
	 * 
	 * @param joinPoint
	 * @param e
	 * @return
	 * @throws Exception
	 */
	private OperationLog generateLog(JoinPoint joinPoint, Exception e)
			throws Exception {

		OperationLog log = new OperationLog();
		// Admin admin = (Admin)
		// this.request.getSession().getAttribute("admin");
		// log.setIntranetID(admin.getIntranetID());
		// log.setFullName(admin.getFullName());
		// log.setCountry(admin.getCountry());
		// log.setUserIP(request.getRemoteAddr());
		// log.setOperateDate(new Timestamp(new Date().getTime()).toString());
		//
		// MethodSignature signature = (MethodSignature)
		// joinPoint.getSignature();
		// log.setClassName(signature.getMethod().getDeclaringClass().getName());
		// log.setMethod(signature.getName());
		//
		// LogMarker marker = (LogMarker) (signature.getMethod()
		// .getAnnotation(LogMarker.class));
		// log.setMessage(marker.desc());
		// log.setComment(e.getMessage());

		return log;
	}
}
