package ru.stqa.manits.manager;

import jakarta.mail.*;
import ru.stqa.manits.model.MailMessage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase {
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String login, String password) {
        try {
            var session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            ;
            store.connect("localhost", login, password);
            var inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            var messages = inbox.getMessages();

            var mails =  Arrays.stream(messages)
                    .map(m-> {
                        try {
                            return new MailMessage()
                            .withFrom(String.valueOf(m.getFrom()[0]))
                            .withContent((String) m.getContent());
                        } catch (MessagingException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();

            inbox.close();
            store.close();

            return mails;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
