package com.unirider.user.domain.model.aggregates;

import com.unirider.iam.domain.model.aggregates.User;
import com.unirider.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Passenger extends AuditableAbstractAggregateRoot<Passenger> {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String preferences = "No preferences provided";

    @NotBlank
    private String phoneNumber = "999 999 999";

    @NotBlank
    private String address = "No address provided";

    @NotBlank
    private String idCardUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Passenger(User user, String firstName, String lastName, String preferences, String phoneNumber, String address, String idCardUrl){
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferences = preferences;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.idCardUrl = idCardUrl;
    }

    public Passenger(){}

    public Long getUserId() {
        return this.user.getId();
    }
}