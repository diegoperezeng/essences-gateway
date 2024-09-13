package com.boticario.essences_gateway.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${users.mock.nameCrypto}")
    private String mockUserName;

    @Value("${users.mock.passwordCrypto}")
    private String mockUserPass;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aqui você seria onde buscar[iamos] o usuário de um banco de dados ou outra fonte de dados.
        // Para fins do teste, estamos retornando um usuário fictício com username e senha fixos parametrizados no yml.
        
        if ("admin".equals(username)) {
            return new User(
                mockUserName,
                mockUserPass,
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
            );
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
    }
}