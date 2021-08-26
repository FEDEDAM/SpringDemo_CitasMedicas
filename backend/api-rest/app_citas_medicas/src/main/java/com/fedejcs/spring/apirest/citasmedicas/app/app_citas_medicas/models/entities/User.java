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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "users" )
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;

    /*========================================================================================*/
    //------                        1) Attrs User --> Tabla users.                      ------//
    /*========================================================================================*/

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column( unique = true, nullable = false )
    private String nick;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column( unique = true, nullable = false )
    @Email
    private String email;

    @NotEmpty
    private String name;
    
    @NotEmpty
    private String lastName;

    @NotEmpty
    private String gender;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "blood_type_id" )
    private BloodType bloodType;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_status_id" )
    private UserStatus status;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_type_id" )
    private UserType type;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;


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
    public User(){/* Empty constructor */}

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}
    public Date getCreateAt() {return this.createAt;}
    public Date getModifiedAt() {return this.modifiedAt;}

    public String getNick() {return this.nick;}
    public void setNick(String nick) {this.nick = nick;}

    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}
    
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public BloodType getBloodType() {return this.bloodType;}
    public void setBloodType(BloodType bloodType) {this.bloodType = bloodType;}

    public UserStatus getStatus() {return this.status;}
    public void setStatus(UserStatus status) {this.status = status;}

    public UserType getType() {return this.type;}
    public void setType(UserType type) {this.type = type;}

    public Date getDateOfBirth() {return this.dateOfBirth;}
    public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public String getGender() {return this.gender;}
    public void setGender(String gender) {this.gender = gender;}




}
