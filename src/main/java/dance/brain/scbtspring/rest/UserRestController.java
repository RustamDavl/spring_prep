package dance.brain.scbtspring.rest;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.dto.UserReadDto;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.mapper.UserMapper;
import dance.brain.scbtspring.service.UserService;
import dance.brain.scbtspring.validation.groups.OnCreate;
import dance.brain.scbtspring.validation.groups.OnUpdate;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/users")
public class UserRestController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserReadDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService
                        .getAll()
                        .stream()
                        .map(userMapper::toDto)
                        .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userMapper.toDto(userService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<UserReadDto> createUser(@RequestBody
                                                  @Validated({Default.class, OnCreate.class})
                                                  CreateUpdateUserDto dto) {
        User newUser = userMapper.toEntity(dto);
        User createdUser = userService.create(dto.getCompanyId(), newUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toDto(createdUser));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserReadDto> updateUser(@RequestBody
                                                  @PathVariable("id") Long id,
                                                  @Validated({Default.class, OnUpdate.class})
                                                  CreateUpdateUserDto dto) {
        User newUser = userMapper.toEntity(dto);
        User updatedUser = userService.update(dto.getCompanyId(), id, newUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
