package identity.TuanHuy.mapper;

    import identity.TuanHuy.dto.request.UserCreationRequest;
    import identity.TuanHuy.dto.request.UserUpdateRequest;
    import identity.TuanHuy.dto.response.UserResponse;
    import identity.TuanHuy.entity.Users;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.MappingTarget;
    import org.mapstruct.factory.Mappers;

    import java.util.List;
    import java.util.Set;
    import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
    public interface UserMapper {
        UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


        Users toUser(UserCreationRequest request);


        void updateUser(@MappingTarget Users users , UserUpdateRequest request); // map the target of the resquest to the user


        @Mapping(target = "roles", expression = "java(mapRoles(users))") // convert roles to Set<String>
        UserResponse toUserResponse(Users users);


        List<UserResponse> toUsersResponse(List<Users> usersList);

        // Hàm custom để map Set<Role> -> Set<String>
        default Set<String> mapRoles(Users users) {
            return users.getRoles() != null
                    ? users.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet())
                    : null;
        }

    }