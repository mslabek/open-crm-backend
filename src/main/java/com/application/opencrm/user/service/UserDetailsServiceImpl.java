package com.application.opencrm.user.service;

import com.application.opencrm.user.model.CurrentUser;
import com.application.opencrm.user.model.User;
import com.application.opencrm.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementing {@link UserDetailsService} for configuring the method for loading the {@link User users}.
 * This implementation in by default used by {@link DaoAuthenticationProvider}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username +
                                                                                       "found."));
        return new CurrentUser(user);
    }

}
