package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointments")
public class Appointment implements Serializable 
{
    private static final long serialVersionUID = 1L;

    /*========================================================================================*/
    //------                     1) ATTR_PATIENTS --> Tabla patients.                   ------//
    /*========================================================================================*/

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern="00:00")
    private Date started;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern="00:00")
    private Date finished;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "patient_id" )
    private Patient patient;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "doctor_id" )
    private Doctor doctor;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "speciality_id" )
    private Speciality speciality;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "appointment_status_id" )
    private AppointmentStatus status;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00")
    private Date createAt;

    @Column(name="modified_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00")
    private Date modifiedAt;

    @PrePersist
    private void preInsert(){createAt = new Date();}

    @PreUpdate
    private void preUpdate(){modifiedAt = new Date();}

    /*========================================================================================*/
    //------                     1) ATTR_PATIENTS --> Tabla patients.                   ------//
    /*========================================================================================*/

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public Date getDate() {return this.date;}
    public void setDate(Date date) {this.date = date;}

    public Date getStarted() {return this.started;}
    public void setStarted(Date started) {this.started = started;}

    public Date getFinished() {return this.finished;}
    public void setFinished(Date finished) {this.finished = finished;}

    public Patient getPatient() {return this.patient;}
    public void setPatient(Patient patient) {this.patient = patient;}

    public Doctor getDoctor() {return this.doctor;}
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}

    public Speciality getSpeciality() {return this.speciality;}
    public void setSpeciality(Speciality speciality) {this.speciality = speciality;}

    public AppointmentStatus getStatus() {return this.status;}
    public void setStatus(AppointmentStatus status) {this.status = status;}

    public Date getCreateAt() {return this.createAt;}
    public Date getModifiedAt() {return this.modifiedAt;}
    
}
