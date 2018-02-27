package com.todolist.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public final class Deal implements Serializable {

    public Deal() {}

    public Deal(String content, boolean completed) {
        this.content = content;
        this.completed = completed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String content;
    @Column
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return id == deal.id &&
                completed == deal.completed &&
                Objects.equals(content, deal.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, completed);
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", completed=" + completed +
                '}';
    }
}
