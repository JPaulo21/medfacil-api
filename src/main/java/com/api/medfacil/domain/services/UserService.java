package com.api.medfacil.domain.services;

import com.api.medfacil.domain.entities.User;
import com.api.medfacil.domain.exceptions.CpfRegisteredException;
import com.api.medfacil.domain.exceptions.FullNumberRegisteredException;
import com.api.medfacil.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user){
        cpfRegistered(user.getCpf());
        numberAlreadyRegistered(user.getContact().getDdi(), user.getContact().getDdd(), user.getContact().getPhoneNumber());
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado")
        );
    }

    public void numberAlreadyRegistered(String ddi, String ddd, String phoneNumber){
        if(userRepository.findByNumber(ddi, ddd, phoneNumber).isPresent())
            throw new FullNumberRegisteredException("Número já cadastrado!");
    }

    public void cpfRegistered(String cpf){
        if(userRepository.findByCpf(cpf).isPresent())
            throw new CpfRegisteredException("Já existe uma conta vinculada ao cpf informado");
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return userRepository.findByCpf(cpf).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado")
        );
    }

    @Transactional
    public User updatePassword(String cpf, String code) {
        User user = userRepository.findByCpf(cpf).orElseThrow(
                        () -> new UsernameNotFoundException("Usuário não encontrado")
        );
        user.setPassword(passwordEncoder.encode(code));
        return userRepository.save(user);
    }

    @Transactional
    public void cleanPassword(String cpf){
        log.info("Limpar senha do usuário: {}", cpf);
        User user = userRepository.findByCpf(cpf).get();
        user.setPassword(null);
    }

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado")
        );
    }
}
