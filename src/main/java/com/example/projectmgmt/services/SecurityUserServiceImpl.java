package com.example.projectmgmt.services;

import com.example.projectmgmt.models.User;
import com.example.projectmgmt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Locale;

@Transactional
@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userrepos;



    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userrepos.findByEmailaddress(s.toLowerCase());
        if (user == null)
        {
            throw new EntityNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailaddress(), user.getPassword(), user.getAuthority());
    }


}
