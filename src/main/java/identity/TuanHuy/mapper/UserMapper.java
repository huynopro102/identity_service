package identity.TuanHuy.mapper;

    import identity.TuanHuy.dto.request.UserCreationRequest;
    import identity.TuanHuy.dto.request.UserUpdateRequest;
    import identity.TuanHuy.dto.response.UserResponse;
    import identity.TuanHuy.entity.Users;
    import org.mapstruct.Mapper;
    import org.mapstruct.MappingTarget;

    @Mapper(componentModel = "spring")
    public interface UserMapper {
        Users toUser(UserCreationRequest request);
        void updateUser(@MappingTarget Users users , UserUpdateRequest request); // map the target of the resquest to the user
        UserResponse toUserResponse(Users users);
    }
