package edu.cibertec.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.cibertec.entity.UsuarioEntity;
import edu.cibertec.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository userRepository;
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UsuarioEntity usuario = userRepository.findByUsuarioAndEstado(username,true);
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        if(usuario != null){
            roles.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
         UserDetails userDetails = new User(usuario.getUsuario(), usuario.getContrasena(), roles);
        return userDetails;
    }
}
