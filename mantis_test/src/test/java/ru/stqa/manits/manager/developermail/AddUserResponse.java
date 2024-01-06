package ru.stqa.manits.manager.developermail;

import ru.stqa.manits.model.DeveloperMailUser;

public record AddUserResponse(Boolean success, Object errors, DeveloperMailUser result) {

}
