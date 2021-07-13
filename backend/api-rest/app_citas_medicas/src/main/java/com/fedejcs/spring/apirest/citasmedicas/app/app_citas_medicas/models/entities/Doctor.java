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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "doctors" )
public class Doctor implements Serializable
{
    private static final long serialVersionUID = 1L;

    /*========================================================================================*/
    //------                      1) ATTR_DOCTOR --> Tabla doctors.                     ------//
    /*========================================================================================*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "doctor_status_id" )
    private DoctorStatus status;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "speciality_id" )
    private Speciality speciality;

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
    //------                   2) GETTERS , SETTERS & BUILDERS PATIENTS                 ------//
    /*========================================================================================*/


    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}
    
    public Date getCreateAt() {return this.createAt;}
    public Date getModifiedAt() {return this.modifiedAt;}

    public DoctorStatus getStatus() {return this.status;}
    public void setStatus(DoctorStatus status) {this.status = status;}

    public Speciality getSpeciality() {return this.speciality;}
    public void setSpeciality(Speciality speciality) {this.speciality = speciality;}

}
