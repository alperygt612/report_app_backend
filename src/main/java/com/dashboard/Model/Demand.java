package com.dashboard.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Table(name = "support")
@Data
public class Demand {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "support_seq")
    @SequenceGenerator(name = "support_seq", sequenceName = "support_message_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "name", nullable = false)  
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public String toString() {
        return "Demand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                '}';
    }
}
