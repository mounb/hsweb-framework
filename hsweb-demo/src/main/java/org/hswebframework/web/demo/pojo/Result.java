package org.hswebframework.web.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {

    private String name;

    private String password;

    public Result(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Result() {
    }
}
