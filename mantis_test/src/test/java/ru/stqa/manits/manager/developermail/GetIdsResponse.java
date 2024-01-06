package ru.stqa.manits.manager.developermail;

import java.util.List;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {

}
