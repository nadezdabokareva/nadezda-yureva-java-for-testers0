package model;

import java.util.Objects;

public record GroupData(String id, String name, String header, String footer) {

    public GroupData() {
        this("", " ", " ", " ");
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.header, this.footer);
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header, this.footer);
    }
    public GroupData withHeader(String header) {
        return new GroupData(this.id, this.name, header, this.footer);
    }
    public GroupData withFooter(String footer) {
        return new GroupData(this.id, this.name, this.header, footer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(id, groupData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}