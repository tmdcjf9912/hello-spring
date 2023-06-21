package hello.hellospring.controller;

public class MemberForm {
    private String Name; //createMemberForm에 있는 name이랑 매칭

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
