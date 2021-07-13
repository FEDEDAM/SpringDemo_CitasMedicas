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
@Table( name="doctors_status" )
public class DoctorStatus implements Serializable
{
    private static final long serialVersionUID = 1L;

    /*========================================================================================*/
    //------               1) ATTR_DoctorStatus --> Tabla doctor_status.                ------//
    /*========================================================================================*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String description;

    @OneToMany( mappedBy = "doctor_status" , fetch = FetchType.LAZY , cascade = CascadeType.ALL )
    private List<Doctor> dotors;

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

    public String getDescription() {return this.description;}   
    public void setDescription(String description) {this.description = description;}

    public List<Doctor> getDotors() {return this.dotors;}
    public void setDotors(List<Doctor> dotors) {this.dotors = dotors;}

}
