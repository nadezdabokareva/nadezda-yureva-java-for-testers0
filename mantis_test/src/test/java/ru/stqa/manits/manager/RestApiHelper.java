package ru.stqa.manits.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import ru.stqa.manits.model.DeveloperMailUser;
import ru.stqa.manits.model.IssueData;
import ru.stqa.manits.model.UserForRegister;

import static io.restassured.RestAssured.given;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }


    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
           new RuntimeException(e);
        }
    }

    public void registrationUser(String username, String realname, String email) {

        UserForRegister user = new UserForRegister("20240107CWH_Z74ctAO0M4lKVdnpEnZ6c3K99197",
                username,
                realname,
                email,
                "25",
                "on");

        UserForRegister data = given()
                .body(user)
                .when()
                .post("http://localhost/mantisbt-2.26.0/api/rest/users/")
                .then().log().all()
                .extract().as(UserForRegister.class);

        String returnEmail = data.getEmail();
        System.out.println(returnEmail);


    }
}
