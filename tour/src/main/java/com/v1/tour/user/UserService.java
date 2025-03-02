package com.v1.tour.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.exception.CustomException;
import com.v1.tour.kafka.PublicMessageKafka;
import com.v1.tour.user.dao.UserDao;
import com.v1.tour.user.dto.UserDto;
import com.v1.tour.user.dto.UserInfoUpdateDto;
import com.v1.tour.userrole.UserRoleService;
import com.v1.tour.userrole.dto.UserRoleDto;
import com.v1.tour.utils.Constants;
import com.v1.tour.utils.Constants.ErrorType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService extends BaseService<UserModel, UserRepository> {
    UserRoleService userRoleService;
    PublicMessageKafka publicMessageKafka;

    public UserService(Repositories repositories, UserRoleService userRoleService,
            PublicMessageKafka publicMessageKafka) {
        super(repositories.userRepository);
        this.userRoleService = userRoleService;
        this.publicMessageKafka = publicMessageKafka;
    }

    @SuppressWarnings("unchecked")
    public List<UserDao> findAll(Integer page) {
        var query = super.entityManager.createNativeQuery("select a.*, array_agg(r.name) as roles " +
                "from account a " +
                "inner join user_role ur on ur.user_id = a.id " +
                "inner join role r on r.id = ur.role_id " +
                "group by a.id",
                UserDao.class);
        query.setFirstResult(page * Constants.PAGE_SIZE);
        query.setMaxResults(Constants.PAGE_SIZE);

        return query.getResultList();
    }

    @Transactional
    public UserModel create(UserDto userDto) {
        validateEmail(userDto, null);
        userDto.setPassword(super.passwordEncoder.encode(userDto.getPassword()));
        var user = super.create(userDto, UserModel.class);
        var userRoleDtos = userDto.getRoleIds().stream()
                .map(roleId -> UserRoleDto.builder().userId(user.getId()).roleId(roleId).build()).toList();
        userRoleService.createMulti(userRoleDtos);
        return user;
    }

    private void validateEmail(UserDto userDto, UUID id) {
        if (userDto.getEmail() == null)
            return;

        var user = super.repository.findByEmail(userDto.getEmail());
        if (user.isPresent() && id == null)
            throw new CustomException(ErrorType.EMAIL_EXIST);

        if (user.isPresent() && !user.get().getId().equals(id))
            throw new CustomException(ErrorType.EMAIL_EXIST);
    }

    @Transactional
    public UserModel update(UUID id, UserDto userDto) {
        validateEmail(userDto, id);

        var user = super.findById(id);
        user.updateByDto(userDto);
        super.repository.save(user);

        if (userDto.getRoleIds() != null && !userDto.getRoleIds().isEmpty()) {
            this.userRoleService.deleteByUserId(id);

            var userRoleDtos = userDto.getRoleIds().stream()
                    .map(roleId -> UserRoleDto.builder().userId(id).roleId(roleId).build()).toList();
            this.userRoleService.createMulti(userRoleDtos);
        }

        publicMessageKafka.publicUpdateUser(user);
        return user;
    }

    public UserModel updateInfo(UUID id, UserInfoUpdateDto userInfoUpdateDto) {
        var user = super.findById(id);
        var userDto = mapper.map(userInfoUpdateDto, UserDto.class);
        user.updateByDto(userDto);
        return super.repository.save(user);
    }

    public Boolean existsByEmail(String email) {
        return super.repository.existsByEmail(email);
    }

    public UserModel findByEmail(String email) {
        return super.repository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorType.EMAIL_NOT_FOUND));
    }
}
