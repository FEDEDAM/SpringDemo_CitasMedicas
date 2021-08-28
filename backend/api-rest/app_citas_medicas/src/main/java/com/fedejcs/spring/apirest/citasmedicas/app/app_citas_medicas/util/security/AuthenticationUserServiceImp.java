package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.security;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IUserDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserServiceImp implements UserDetailsService
{
    @Autowired
    private IUserDAO userDao;

    private Logger logger = LoggerFactory.getLogger( AuthenticationUserServiceImp.class );

    @Override
    public UserDetails loadUserByUsername( String nick ) 
        throws UsernameNotFoundException 
    {
        User user = userDao.findByNick( nick );

        if( user == null ){
            String msgLogger = "Error in authentication. Dont exists the user '".concat( nick) .concat( "' in the system." );
            String msgException = "Error in authentication. Dont exists the user '".concat( nick ).concat( "' in the system." );
            logger.error( msgLogger );
            throw new UsernameNotFoundException( msgException );
        }

        if( user.getType() == null ){
            String msgLogger = "Error to load the user. Dont assignament rol to the user'".concat( nick ).concat( "'" );
            String msgException = "Error to load the user. Dont assignament rol to the user'".concat( nick ).concat( "'" );
            logger.error( msgLogger );
            throw new UsernameNotFoundException( msgException );
        } 

        return AuthenticationUser.build(user);
    }
}




