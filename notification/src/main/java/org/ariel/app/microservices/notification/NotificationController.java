package org.ariel.app.microservices.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ariel.app.microservices.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("Sending notification");
        notificationService.sendNotification(notificationRequest);
    }
}
