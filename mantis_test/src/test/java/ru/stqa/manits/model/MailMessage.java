package ru.stqa.manits.model;

import ru.stqa.manits.manager.MailHelper;

public record MailMessage(String from, String content) {
    public MailMessage(){
        this("", "");
    }

    public MailMessage withFrom(String from){
        return new MailMessage(from, this.content);
    }
    public MailMessage withContent(String content){
        return new MailMessage(this.from, content);
    }
}