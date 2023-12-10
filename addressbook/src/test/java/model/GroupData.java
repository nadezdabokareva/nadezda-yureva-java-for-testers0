package model;

public record GroupData(String id, String header, String footer, String name) {

    public GroupData() {
        this("", " ", " ", " ");
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, this.header, this.footer, name);
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.header, this.footer, this.name);
    }
    public GroupData withHeader(String header) {
        return new GroupData("", header, this.footer, this.name);
    }
    public GroupData withFooter(String footer) {
        return new GroupData("", this.header, footer, this.name);
    }
}