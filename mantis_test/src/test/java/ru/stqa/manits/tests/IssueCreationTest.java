package ru.stqa.manits.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import ru.stqa.manits.model.IssueData;

public class IssueCreationTest extends TestBase{

    @Test
    public void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(RandomStringUtils.randomAlphabetic(10))
                .withDescription(RandomStringUtils.randomAlphabetic(50))
                .withProject(1L));
    }
}
