package entity;

import converter.BirthdayConvertor;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;

    private String name;

    private String surname;

    // @Convert(converter = BirthdayConvertor.class)
    @Column(name = "birth_day")
    private Birthday birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Type(JsonBinaryType.class)
    private String info;
}
