package ru.stqa.manits.manager;

import jakarta.mail.*;
import ru.stqa.manits.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase {
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String login, String password, Duration duration) throws InterruptedException {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
            try {
                var inbox = getInbox(login, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();

                var mails = Arrays.stream(messages)
                        .map(m -> {
                            try {
                                return new MailMessage()
                                        .withFrom(String.valueOf(m.getFrom()[0]))
                                        .withContent((String) m.getContent());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        }).toList();

                inbox.close();
                inbox.getStore().close();

                if (mails.size() > 0) {
                    return mails;
                }

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            Thread.sleep(1000);
        }
        throw new RuntimeException("No mail");
    }

    private static Folder getInbox(String login, String password)  {
        try {
            var session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            store.connect("localhost", login, password);
            var inbox = store.getFolder("INBOX");
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void drain(String login, String password) {

        try {
            var inbox = getInbox(login, password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
