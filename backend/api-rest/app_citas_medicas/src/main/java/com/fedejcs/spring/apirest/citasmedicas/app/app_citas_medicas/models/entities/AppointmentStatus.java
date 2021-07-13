package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name="appointment_status" )
public class AppointmentStatus implements Serializable
{
    private static final long serialVersionUID = 1L;

    /*========================================================================================*/
    //------              1) ATTR_PATIENTS --> Tabla appointment_status.                ------//
    /*========================================================================================*/
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String description;

    @OneToMany( mappedBy = "appointment_status" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifedAt;

    @PrePersist
    public void prePersist(){createAt = new Date();}

    @PreUpdate
    public void preUpdate(){ modifedAt = new Date();}


    /*========================================================================================*/
    //------                 2) GETTERS , SETTERS & BUILDERS APPOINTMENTS               ------//
    /*========================================================================================*/


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}

    public List<Appointment> getAppointments() {return this.appointments;}
    public void setAppointments(List<Appointment> appointments) {this.appointments = appointments;}

    public Date getCreateAt() {return this.createAt;}
    public Date getModifedAt() {return this.modifedAt;}


}
