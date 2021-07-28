package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="patients")
public class Patient implements Serializable
{
    private static final long serialVersionUID = 1L;

    /*========================================================================================*/
    //------                     1) ATTR_PATIENTS --> Tabla patients.                   ------//
    /*========================================================================================*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "patient" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "patient_status_id")
    private PatientStatus status;

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

    public Patient(){
        appointments = new ArrayList<>();
    }

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}
    
    public Date getCreateAt() {return this.createAt;}
    public Date getModifiedAt() {return this.modifiedAt;}

    public List<Appointment> getAppointments() {return this.appointments;}
    public void setAppointments(List<Appointment> appointments) {this.appointments = appointments;}

    public PatientStatus getStatus() {return this.status;}
    public void setStatus(PatientStatus status) {this.status = status;}
    

}
