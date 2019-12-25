package com.example.aviasales2.service.impl;

import com.example.aviasales2.Constants;
import com.example.aviasales2.config.filterConfig.UserFilter;
import com.example.aviasales2.entity.QUser;
import com.example.aviasales2.entity.Sender;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.Wallet;
import com.example.aviasales2.entity.transferObjects.UserDTO;
import com.example.aviasales2.entity.transferObjects.WalletDTO;
import com.example.aviasales2.repository.UserRepository;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.UserService;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DozerBeanMapper mapper;
    private final WalletRepository walletRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DozerBeanMapper mapper, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.walletRepository = walletRepository;
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findByUserId(id);
    }


    @Override
    public List <User> findAll(UserFilter userFilter) {
        final QUser qUser = QUser.user;

        BooleanBuilder builder = new BooleanBuilder(qUser.isNotNull());
        if (userFilter.getRole() != null) {
            builder.and(qUser.role.eq(userFilter.getRole()));
        }
        return (List <User>) userRepository.findAll(builder);
    }

    @Override
    public List <User> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List <User> findByLastNameAndEmail(String lastName, String email) {
        return userRepository.findByLastNameAndEmail(lastName, email);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void sendPasswordToEmail(String userName) {
        User user = findByUserName(userName);
        String generatedPassword = RandomStringUtils.randomAlphanumeric(6);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setHashPass(bCryptPasswordEncoder.encode(generatedPassword));
        update(user);

        StringBuilder html = new StringBuilder();
        html.append("<html>\n");

        html.append("<body>\n");
        html.append("<h2>Здравствуйте, ").append(user.getFirstName()).append("!</h2>\n");
        html.append("<p>Вы отправили запрос на восстановление пароля.</p>\n");
        html.append("<p>Ваш новый пароль: ").append(generatedPassword).append("</p>\n");

        html.append("</body>\n");
        html.append("</html>");

        Sender sender = new Sender();
        String url = Constants.URL;
        String subject = "Смена пароля";
        sender.send(subject, html.toString(), user.getEmail());
    }

    @Transactional
    @Override
    public void update(User user) {
        BigDecimal sum;
        Wallet wallet = walletRepository.findByWalletId(user.getWallet().getWalletId());
        sum = wallet.getSum();
        userRepository.save(mapper.map(user, User.class));
        wallet.setSum(sum);
        walletRepository.save(wallet);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findByUserId(id);
        if (user.getRole().equals("USER")) {
            userRepository.deleteByUserId(id);
        }
    }

    @Override
    public User findByHashConfirm(String hashConfirm) {
        return userRepository.findByConfirmingHash(hashConfirm);
    }

    @Transactional
    @Override
    public void lockUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (!user.getRole().equals("ADMIN")) {
            user.setLocked(true);
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void unlockUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (!user.getRole().equals("ADMIN")) {
            user.setLocked(false);
            userRepository.save(user);
        }
    }
}
