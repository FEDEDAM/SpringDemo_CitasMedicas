package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IUserDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements IUserService
{
    @Autowired
    private IUserDAO userRepository;

    @Override
    @Transactional( readOnly = true )
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public User findById(Long id) {
        return userRepository.findById( id ).orElse( null );
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save( user );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById( id );
    }
    
}
