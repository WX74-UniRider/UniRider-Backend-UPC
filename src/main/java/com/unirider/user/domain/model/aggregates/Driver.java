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
public class Driver extends AuditableAbstractAggregateRoot<Driver> {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber = "999 999 999";

    @NotBlank
    private String vehiclePlate = "No vehicle plate provided";

    @NotBlank
    private String insurance = "No insurance provided";

    @NotBlank
    private String vehicleModel = "No vehicle model provided";

    @NotBlank
    private String destino = "No destination provided";


    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;


    public Driver(User user, String firstName, String lastName, String phoneNumber, String vehiclePlate, String insurance, String vehicleModel){
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.vehiclePlate = vehiclePlate;
        this.insurance = insurance;
        this.vehicleModel = vehicleModel;
    }


    public Driver(){}

    public Long getUserId() {
        return this.user.getId();
    }
}