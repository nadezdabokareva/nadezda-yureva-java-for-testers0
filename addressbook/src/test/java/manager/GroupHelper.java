package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper (ApplicationManager manager) {
        super(manager);
    }

    //Открыть страницу с группами
    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    //Нажать кнопку Новая группа (New group)
    private void initGroupCreation() {
        click(By.name("new"));
    }

    //Заполнить поля в форме создания группы (поля заполняются привызове метода type)
    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    //Полный метод создания группы
    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    //Редактировать группу
    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    //Удалить группу
    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    //Подтверждение создания группы Enter
    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    //Удалить выбранную группу
    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    //Вернуться к странице с группами
    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    //Подтвердить редактирование группы Update
    private void submitGroupModification() {
        click(By.name("update"));
    }

    //Редактировать группу - нажать кнопку Edit
    private void initGroupModification() {
        click(By.name("edit"));
    }

    //Выбрать первую группу из списка
    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    //Проверка количества групп в спсике
    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroup() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkBoxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkBox : checkBoxes) {
            checkBox.click();
        }
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            var checkBox = span.findElement(By.name("selected[]"));
            var id = checkBox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}

