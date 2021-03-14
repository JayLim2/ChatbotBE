package ru.sergei.komarov.chatbot.be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_id_seq")
    @SequenceGenerator(name = "topic_id_seq", allocationSize = 1)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "config_topic_mappings",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "config_id")
    )
    @JsonIgnore
    private List<ProfileConfiguration> configuration;

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

    public List<ProfileConfiguration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<ProfileConfiguration> configuration) {
        this.configuration = configuration;
    }
}
