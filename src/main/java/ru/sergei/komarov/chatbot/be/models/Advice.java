package ru.sergei.komarov.chatbot.be.models;

import javax.persistence.*;

@Entity
@Table(name = "advices")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advice_id_seq")
    @SequenceGenerator(name = "advice_id_seq")
    private int id;

    @Column(name = "advice_type")
    private AdviceType adviceType;

    @Column(name = "mistake_type")
    private MistakeType mistakeType;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

}
