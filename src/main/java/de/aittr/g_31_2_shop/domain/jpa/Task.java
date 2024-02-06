package de.aittr.g_31_2_shop.domain.jpa;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="description")
    private String descriiption;
    @Column(name="executed_at")
    private Timestamp executedAt;

    public Task() {
        executedAt = new Timestamp(System.currentTimeMillis());
    }

    public Task(String descriiption) {
        this.descriiption = descriiption;
        executedAt = new Timestamp(System.currentTimeMillis());
    }

    public String getDescriiption() {
        return descriiption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(descriiption, task.descriiption) && Objects.equals(executedAt, task.executedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descriiption, executedAt);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", descriiption='" + descriiption + '\'' +
                ", executedAt=" + executedAt +
                '}';
    }
}
