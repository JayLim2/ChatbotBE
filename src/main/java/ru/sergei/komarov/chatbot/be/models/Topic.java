package ru.sergei.komarov.chatbot.be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_id_seq")
    @SequenceGenerator(name = "topic_id_seq", allocationSize = 1)
    private int id;

    private String name;

    //FIXME many-to-many!
    @ManyToOne
    @JsonIgnore
    private ProfileConfiguration configuration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ProfileConfiguration configuration) {
        this.configuration = configuration;
    }
}
