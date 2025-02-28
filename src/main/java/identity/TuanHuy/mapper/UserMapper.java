package identity.TuanHuy.mapper;

    import identity.TuanHuy.dto.request.UserCreationRequest;
    import identity.TuanHuy.dto.request.UserUpdateRequest;
    import identity.TuanHuy.dto.response.UserResponse;
    import identity.TuanHuy.entity.Permission;
    import identity.TuanHuy.entity.Role;
    import identity.TuanHuy.entity.RoleEnum;
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
                    ? users.getRoles()
                    .stream()
                    .map(role -> role.getName().toString())
                    .collect(Collectors.toSet())
                    : null;
            //  role.getName().name()).collect(Collectors.toSet()
            // .map(Role::getDescription) -> Stream<String> , lưu ý là Role trong .map(Role::getDescription) là 1 entity phải viết Hoa lên
        }


        // trong user ko có nên dùng entity Role để tìm ra list<permission>
        default Set<String> mapPermissions(Users user){
            return user.getRoles() != null ?
                user.getRoles().stream()
                        // map(role -> role.getPermission)) , if write here , map will convert element role to Stream<Permission>
                        // but if .Stream() continue it return Stream<Stream<Permission>>
                        .flatMap(role -> role.getPermissions().stream())
                        .map(Permission::getName)
                        .collect(Collectors.toSet())
                        : null;
        }
// user.getRoles().stream() -> Stream<Role>
// case 1 : map(role -> role.getPermission()) -> Stream<Set<Permission>>
// case 2 : flatMpa(role -> role.getPermission().stream() ) -> Stream<Permission>
    }