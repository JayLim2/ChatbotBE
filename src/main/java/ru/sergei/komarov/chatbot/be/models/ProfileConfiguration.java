package ru.sergei.komarov.chatbot.be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profile_configs")
public class ProfileConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_config_id_seq")
    @SequenceGenerator(name = "profile_config_id_seq")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "config_topic_mappings",
            joinColumns = @JoinColumn(name = "config_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> preferredTopics;

    @Column(name = "lang_skills_config")
    @Type(type = "jsonb")
    private JsonNode languageSkillsConfig;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Topic> getPreferredTopics() {
        return preferredTopics;
    }

    public void setPreferredTopics(List<Topic> preferredTopics) {
        this.preferredTopics = preferredTopics;
    }

    public JsonNode getLanguageSkillsConfig() {
        return languageSkillsConfig;
    }

    public void setLanguageSkillsConfig(JsonNode languageSkillsConfig) {
        this.languageSkillsConfig = languageSkillsConfig;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
