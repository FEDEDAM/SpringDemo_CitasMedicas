package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.ArrayList;
import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IUserDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringUserDetailsService implements UserDetailsService
{
    @Autowired
    private IUserDAO userDao;

    private Logger logger = LoggerFactory.getLogger( SpringUserDetailsService.class );

    @Override
    public UserDetails loadUserByUsername( String nickOrEmail ) 
        throws UsernameNotFoundException 
    {
        User user = userDao.findByNickOrEmail( nickOrEmail );

        if( user == null ){
            logger.error( "Error in authentication. Dont exists the user '".concat( nickOrEmail).concat( "' in the system." ) );
            throw new UsernameNotFoundException( "Error in authentication. Dont exists the user '"
                                                    .concat( nickOrEmail).concat( "' in the system." ) 
                                                );
        }

        List< GrantedAuthority > authorities = new ArrayList< GrantedAuthority >();

        if( user.getType() == null ){
            logger.error( "Error to load the user. Dont assignament rol to the user'".concat( nickOrEmail).concat( "'" ) );
            throw new UsernameNotFoundException( "Error to load the user. Dont assignament rol to the user'"
                                                    .concat( nickOrEmail).concat( "'" ) 
                                                );
        
        } else authorities.add( new SimpleGrantedAuthority( "ROL_".concat( user.getType().getName().toUpperCase() ) ) );


        return new org.springframework.security.core.userdetails.User( 
            user.getNick() , user.getPassword() , true , true , true , true , authorities 
        );
    } 
}
