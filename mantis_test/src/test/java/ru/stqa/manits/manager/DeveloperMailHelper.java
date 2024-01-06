package ru.stqa.manits.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeUtility;
import okhttp3.*;
import ru.stqa.manits.manager.developermail.AddUserResponse;
import ru.stqa.manits.manager.developermail.GetIdsResponse;
import ru.stqa.manits.manager.developermail.GetMessageResponse;
import ru.stqa.manits.model.DeveloperMailUser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.CookieManager;
import java.time.Duration;

public class DeveloperMailHelper extends HelperBase {
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client;
    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public DeveloperMailUser addUser() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url("https://www.developermail.com/api/v1/mailbox")
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            var text = response.body().string();
            var addUserResponse = new ObjectMapper().readValue(text, AddUserResponse.class);
            if (!addUserResponse.success()) {
                throw new RuntimeException(addUserResponse.errors().toString());
            }
            return addUserResponse.result();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(DeveloperMailUser user) {
        Request request = new Request.Builder()
                .url(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()))
                .header("X-MailboxToken", user.token())
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String receive(DeveloperMailUser user, Duration duration) throws InterruptedException, MessagingException {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
        var text1 = get(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()), user.token());
            GetIdsResponse response1 = null;
            try {
                response1 = new ObjectMapper().readValue(text1, GetIdsResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (!response1.success()) {
                throw new RuntimeException(response1.errors().toString());
            }
            if (response1.result().size() > 0) {
                var text2 = get(String.format("https://www.developermail.com/api/v1/mailbox/%s/messages/%s", user.name(),
                        response1.result().get(0)), user.token());
                GetMessageResponse response2 = null;
                try {
                    response2 = new ObjectMapper().readValue(text2, GetMessageResponse.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                if (!response2.success()) {
                    throw new RuntimeException(response2.errors().toString());
                }
                try {
                    var result3 = new String(MimeUtility.decode(
                            new ByteArrayInputStream(response2.result().getBytes()),
                            "quoted-printable").readAllBytes());
                    return result3;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
                Thread.sleep(1000);
        }
        throw new RuntimeException("No mail");

    }

    public String get(String url, String token) {
        Request request = new Request.Builder()
                .url(url)
                .header("X-MailboxToken", token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
