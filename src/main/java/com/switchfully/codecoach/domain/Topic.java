package com.switchfully.codecoach.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topics")
public class Topic {

    public enum TopicName {JAVA, PYTHON, CSHARP, CSS, HTML, JAVASCRIPT, TYPESCRIPT, ANGULAR, SPRING, SQL}

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic_name")
    private TopicName topicname;

    @Column(name = "experience")
    private int experience;


}
