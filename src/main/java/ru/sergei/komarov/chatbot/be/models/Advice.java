package ru.sergei.komarov.chatbot.be.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "advices")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advice_id_seq")
    @SequenceGenerator(name = "advice_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "advice_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdviceType adviceType;

    @Column(name = "mistake_type")
    @Enumerated(EnumType.STRING)
    private MistakeType mistakeType;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    @JsonIgnoreProperties({"advices"})
    private Message message;

    @Column(name = "start_position")
    private int startPosition;

    @Column(name = "end_position")
    private int endPosition;

    private String comment;

    @Column(name = "custom_data", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private JsonNode customData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdviceType getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(AdviceType adviceType) {
        this.adviceType = adviceType;
    }

    public MistakeType getMistakeType() {
        return mistakeType;
    }

    public void setMistakeType(MistakeType mistakeType) {
        this.mistakeType = mistakeType;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public JsonNode getCustomData() {
        return customData;
    }

    public void setCustomData(JsonNode customData) {
        this.customData = customData;
    }
}
