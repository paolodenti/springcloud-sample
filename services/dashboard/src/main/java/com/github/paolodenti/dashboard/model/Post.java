package com.github.paolodenti.dashboard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Post {

    private long userId;

    private long id;

    private String title;

    private String body;
}
