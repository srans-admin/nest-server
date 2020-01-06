package com.srans.nestserver.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Notification;
import com.srans.nestserver.repository.NotificationRepository;
import com.srans.nestserver.util.NSException;

@CrossOrigin(value = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class NotificationController {
	Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
	private NotificationRepository notificationRepo;
	
	
 
	
	@GetMapping(value = "/users/{userId}/notifications")
	@PreAuthorize("permitAll()")
	public Notification getAllUserNotification(@PathVariable(value = "userId") Long userId,   Notification notifications) throws NSException {
		logger.info("In::getAllNotificationsOfUser::" );
		List<Object[]> response = notificationRepo.getAllNotification(userId);
		 for(Object[] s:response) {
			
			 notifications.setId(((BigInteger) s[0]).longValue());
			 notifications.setMessage((String) s[1]);
		 
		 }
		
		logger.info("Out::getAllNotificationsOfUser::"+response );
		return notifications ;
	}
	
	
/*
	@PostMapping(value = "/notification")
	@PreAuthorize("permitAll()")
	public Notification notify(@Valid @RequestBody Notification notify) throws NSException {
		logger.info("In::notification::" + notify);

		Notification notification = notificationRepo.save(notify);
		
	    String superAdminUserName = null;
		Long superAdminCode = (long) 0;
		String url = "http://localhost:9090/uaa-server/v1/superAdminUserName";
		Object[] object = restTemplate.getForObject(url, Object[].class);

		Long code = (long) 1;
		Long[] getNotificationId = notificationRepo.getNotificationId();
		for (int i = 0; i < object.length; i++) {

			System.out.println(object.length);
			superAdminUserName = (String) object[i];
			superAdminCode = code * 100;

			for (int j = 0; j < getNotificationId.length; j++) {

				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(getNotificationId[j]);
				notificationUser.setSuperAdminCode(superAdminCode);
				notificationUser.setsuperAdminUsername(superAdminUserName);
				notificationUserRepo.save(notificationUser);

			}

			code++;
		}
		
		logger.info("Out::notification::" + notify);
		return (notification);

	}

	@GetMapping("/notification/{codeId}/")
	@PreAuthorize("permitAll()")
	public Set<NotificationUtility> getSuperAdminUsername(@PathVariable(value = "codeId") Long superAdminCodeId)
			throws NSException {

		Set<Object> notificationInfo = notificationRepo.findNewNotification(""+superAdminCodeId);

		Set<NotificationUtility> notifydata = new HashSet<>();
		for (Iterator<Object> iterator = notificationInfo.iterator(); iterator.hasNext();) {

			Object[] obj = (Object[]) iterator.next();

			NotificationUtility notificationData = new NotificationUtility();
			for (int i = 0; i < obj.length; i++) {

				if (i == 0) {
					notificationData.setMessage((String) obj[i]);

				} else if (i == 1) {
					notificationData.setNotificationCategory((String) obj[i]);
				}

				else if (i == 2) {
					notificationData.setUserRole((String) obj[i]);
				} else if (i == 3) {
					notificationData.setViewStatus((Character) obj[i]);
				} else if (i == 4) {
					notificationData.setNotificationId(((BigInteger) obj[i]).longValue());
				}
				notifydata.add(notificationData);
			}

		}

		return (notifydata);

	}

	@PutMapping("/notification/{notificationId}")
	@PreAuthorize("permitAll()")

	public Notification updateNotification(@PathVariable Long notificationId,
			@Valid @RequestBody Notification notificationRequest) {

		return notificationRepo.findById(notificationId).map(notification -> {

			notification.setViewStatus(notificationRequest.getViewStatus());

			return notificationRepo.save(notification);
		}).orElseThrow(() -> new ResourceNotFoundException("NotificationId " + notificationId + " not found"));
	}

	@DeleteMapping("/notification/{notificationId}")
	@PreAuthorize("permitAll()")

	public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
		return notificationRepo.findById(notificationId).map(notification -> {
			notificationRepo.delete(notification);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("NotificationId " + notificationId + " not found"));
	}*/

}
