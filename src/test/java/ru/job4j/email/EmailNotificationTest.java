package ru.job4j.email;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailNotificationTest {
    @Test
    public void whenDoOneTask() {
        User user = new User("Vasya", "ivanov_v@gmail.com");
        EmailNotification emailNotification = new EmailNotification();
        assertThat(emailNotification.emailTo(user)).isDone();
    }
}