package web_run.hellospring.domain;

import java.util.Objects;

// 회원 도메인과 리포지토리 만들기
/* 리포지토리는 현재 고민하고 중이며 어떤 데이터베이스를 사용할지는 정해지지 않아서 인터페이스로 상속을 받는다는 가정을 한다.  */
public class Member {
    private long ID;
    private String name;

    public long getID() {
        return ID;
    }

    public void setID(Long id) {
        this.ID = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
