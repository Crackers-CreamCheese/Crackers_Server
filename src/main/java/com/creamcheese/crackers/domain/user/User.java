package com.creamcheese.crackers.domain.user;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "user_id", updatable = false)
    private String userId;

    @NotNull
    @Column(name = "user_pw")
    private String userPw;

    @NotNull
    @Column(name = "user_name", unique = true)
    private String userName;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createdDate;
}
