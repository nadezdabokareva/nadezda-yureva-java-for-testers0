package tests.ContactTest;

import org.junit.jupiter.api.Test;
import tests.TestBase;

public class DeleteContactTest extends TestBase {

    @Test
    public void canDeleteContact() {
        app.contacts().deleteContact();
    }
}
