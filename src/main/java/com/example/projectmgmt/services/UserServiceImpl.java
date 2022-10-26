package com.example.projectmgmt.services;

import com.example.projectmgmt.models.*;
import com.example.projectmgmt.repositories.RoleRepository;
import com.example.projectmgmt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Override
    public User findUserById(long userid)
    {
        return userrepos.findById(userid)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByUserid(long userid)
    {
        User uu = userrepos.findByUserid(userid);
        if (uu == null)
        {
            throw new EntityNotFoundException();
        }
        return uu;
    }

    @Override
    public User findByEmailaddress(String emailaddress)
    {
        User uu = userrepos.findByEmailaddress(emailaddress.toLowerCase());
        if (uu == null)
        {
            throw new EntityNotFoundException("Email " + emailaddress + " not found!");
        }
        return uu;
    }

    /*@Override
    public User findByUserid(long userid)
    {
        User uu = userrepos.findByUserid(userid);
        if (uu == null)
        {
            throw new EntityNotFoundException();
        }
        return uu;
    }*/

    @Override
    public List<User> findAllUsers()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }


    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        if (user.getUserid() != 0)
        {
            userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User " + user.getUserid() + " Not Found"));
            newUser.setUserid(user.getUserid());
        }
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmailaddress(user.getEmailaddress());
        newUser.setOrganization(user.getOrganization());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.getAssignedtickets().clear();
        for (Ticket t : user.getAssignedtickets())
        {
            Ticket newTicket = new Ticket();
            newTicket.setSubject(t.getSubject());
            newTicket.setStatus(t.getStatus());
            newTicket.setAssignee(newUser);

            newUser.getAssignedtickets().add(newTicket);
        }
        for (UserRoles ur : user.getRoles())
        {
            UserRoles newUserRoles = new UserRoles();
            newUserRoles.setRole(ur.getRole());
            newUserRoles.setUser(newUser);

            newUser.getRoles().add(newUserRoles);
        }

        return userrepos.save(newUser);
    }

    @Override
    public List<User> findAllUsersByOrganization(Organization organization)
    {
        List<User> list = new ArrayList<>();
        userrepos.findUsersByOrganization(organization)
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Transactional
    @Override
    public User update(User user, long userid)
    {
        User currentUser = userrepos.findById(userid).orElseThrow(()-> new EntityNotFoundException("User #" + userid + " not found"));
        if(user.getFirstname() != null)
        {
            currentUser.setFirstname(user.getFirstname());
        }
        if(user.getLastname() != null)
        {
            currentUser.setLastname(user.getLastname());
        }
        if (user.getPassword() != null)
        {
            currentUser.setPassword(user.getPassword());
        }
        if (user.getRoles().size() > 0)
        {
            currentUser.getRoles().clear();
            for (UserRoles r: user.getRoles())
            {
                Role newRole = rolerepos.findByName(r.getRole().getName());
                UserRoles newUserRoles = new UserRoles();
                newUserRoles.setUser(currentUser);
                newUserRoles.setRole(newRole);
                currentUser.getRoles().add(newUserRoles);
                System.out.println(newRole);
            }

        }
       return userrepos.save(currentUser);


    }


}



