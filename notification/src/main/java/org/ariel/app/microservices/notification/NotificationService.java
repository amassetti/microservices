package org.ariel.app.microservices.notification;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ariel.app.microservices.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        log.info("Sending notification {}", notificationRequest);
        Notification notification = Notification.builder()
                .message(notificationRequest.message())
                .sender("TBD")
                .sentAt(LocalDateTime.now())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .toCustomerId(notificationRequest.toCustomerId())
                .build();
        notificationRepository.saveAndFlush(notification);

        // todo: send message to notifiction queue
    }

}
