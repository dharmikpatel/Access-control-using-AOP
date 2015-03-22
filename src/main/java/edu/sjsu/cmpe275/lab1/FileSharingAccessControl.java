
package edu.sjsu.cmpe275.lab1;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class FileSharingAccessControl {
	Logger log = Logger.getLogger(FileSharingAccessControl.class.getName());

	@Around("execution(* edu.sjsu.cmpe275.lab1.FileSharing.shareFile(..))")//Around Advice for the sharing of file
	public void validate(ProceedingJoinPoint jp) throws Throwable {

		Object[] args = jp.getArgs();

		if(args.length > 0) {
			String userId = (String)args[0]; //User name for access 
			String targetUserId = (String)args[1];//User name for which the access is allowed
			String filePath = (String)args[2];//Filepath to be share or unshare

			if(userId.equals(targetUserId)) {
				return;
			}

			if(FileSharing.Users.containsKey(userId)) {
				if(filePath.contains(userId)) {
					log.info(userId+" shares the file "+filePath+" with "+targetUserId);
					
					jp.proceed();
				} else {
					if(FileSharing.Users.get(userId).contains(filePath)) {
						log.info(userId+" shares the file "+filePath+" with "+targetUserId);
					
						jp.proceed();
					} else {
						log.info(userId+" cannot share the file "+filePath+" with "+targetUserId);
						throw new UnAuthorisedException(userId+" cannot share the file "+filePath+" with "+targetUserId);
					}
				}
			} else {
				log.info("User: "+userId+" does not exist");
				throw new UnAuthorisedException("User: "+userId+" does not exist");
			}

		} else {
			log.info("Invalid number of arguments");
			throw new Exception("Invalid number of arguments");
		}

	}

	@Around("execution(* edu.sjsu.cmpe275.lab1.FileSharing.unshareFile(..))")//Around Advice for the unsharing of file
	public void validateUnShare(ProceedingJoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		if(args.length > 0) {
			String userId = (String)args[0];
			String targetUserId = (String)args[1];
			String filePath = (String)args[2];

			if(userId.equals(targetUserId)) {
				return;
			}

			if(FileSharing.Users.containsKey(userId)) {
				if(filePath.contains(userId)) {
					log.info(userId+" unshares the file "+filePath+" with "+targetUserId);
					
					jp.proceed();
					for(String str : FileSharing.Users.keySet()) {
						if(!str.equals(targetUserId) && !str.equals(userId)) {
							if(FileSharing.Users.get(str).contains(filePath)) {
								args[0] = userId;
								args[1] = str;
								args[2] = filePath;
								log.info(userId+" unshares the file "+filePath+" with "+str);
								
								jp.proceed(args);
							}
						}
					}
					
				}
			} else {
				log.info("User: "+userId+" does not exist");
				throw new UnAuthorisedException("User: "+userId+" does not exist");
			}

		} else {
			log.info("Invalid number of arguments");
			throw new Exception("Invalid number of arguments");
		}
	}
	
	
	@Around("execution(* edu.sjsu.cmpe275.lab1.FileSharing.readFile(..))")//Around Advice for the reading of file
	public byte[] validateReadFile(ProceedingJoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		if(args.length > 0) {
			String userId = (String)args[0];
			String filePath = (String)args[1];
			
			if(FileSharing.Users.containsKey(userId)) {
				if(filePath.contains(userId)) {
					log.info(userId+" reads the file "+filePath);
					
					byte[] b = (byte[]) jp.proceed();
					return b;
				} else {
					if(FileSharing.Users.get(userId).contains(filePath)) {
						log.info(userId+" reads the file "+filePath);
						
						byte[] b = (byte[]) jp.proceed();
						return b;
					} else {
						log.info(userId+" cannot read the file "+filePath);
						throw new UnAuthorisedException(userId+" cannot read the file "+filePath);
					
					}
				}
			} else {
				log.info("User: "+userId+" does not exist");
				throw new UnAuthorisedException("User: "+userId+" does not exist");
			}
			
		} else {
			log.info("Invalid number of arguments");
			throw new Exception("Invalid number of arguments");
		}
		
	}

}
