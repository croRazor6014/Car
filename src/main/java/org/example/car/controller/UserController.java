package org.example.car.controller;

import static org.example.car.config.RestURIConstants.USER;
import static org.example.car.config.RestURIConstants.UUID;
import static org.example.car.util.ResponseHelper.responseHttpOk;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.example.car.model.User;
import org.example.car.model.dto.UserDto;
import org.example.car.model.exception.NonExistentUserException;
import org.example.car.model.jsonviews.View;
import org.example.car.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lovro.vrlec on Apr,2021
 *
 * UserController is Rest API controller for User object.
 */

@Slf4j
@Tag(name = "User Controller", description = "User Controller")
@RestController
@CrossOrigin
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  /**
   * Rest API "/api/user/{uuid}" for fetching information about user object.
   *
   * @param uuid User id
   * @return User object {@link org.example.car.model.User}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "Find User by UUID",
      description = "Returns a User object",
      tags = {"User"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "User not found.")})
  @GetMapping(value = UUID, produces = "application/json")
  public @ResponseBody
  ResponseEntity<User> findUserByUUID(
      @Parameter(description = "UUID of the User. Cannot be empty.", required = true)
      @PathVariable("uuid") final java.util.UUID uuid) throws NonExistentUserException {
    return responseHttpOk(userService.findUserByUUID(uuid).orElseThrow(NonExistentUserException::new));
  }

  /**
   * Rest API "/api/user/{uuid}" for saving updated User objects.
   *
   * @param userDto User dto
   * @return User object {@link org.example.car.model.User
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "updates User object",
      description = "Returns a User object",
      tags = {"User"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "User not found.")})
  @PutMapping(value = UUID, produces = "application/json")
  public @ResponseBody
  ResponseEntity<User> updateUser(
      @Parameter(description = "UUID of the User. Cannot be empty.", required = true)
      @PathVariable("uuid") final java.util.UUID uuid,
      @RequestBody UserDto userDto) throws NonExistentUserException {
    User dbUser = userService.findUserByUUID(uuid).orElseThrow(NonExistentUserException::new);
    userDto.setUuid(uuid);
    userDto.setId(dbUser.getId());
    return responseHttpOk(userService.updateUser(convertToEntity(userDto)));
  }

  /**
   * Rest API "/api/user" for saving new User objects.
   *
   * @param userDto User dto
   * @return User object {@link org.example.car.model.User}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "adds User object",
      description = "Returns a User object",
      tags = {"User"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "User not found.")})
  @PostMapping(produces = "application/json")
  public @ResponseBody
  ResponseEntity<User> addUser(
      @RequestBody UserDto userDto) {
    return responseHttpOk(userService.addUser(convertToEntity(userDto)));
  }

  /**
   * Rest API "/api/user" for fetching information about car objects.
   *
   * @param specification query params for User
   * @param pageable page
   * @return User object {@link org.example.car.model.User}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "Find User",
      description = "Returns a page with User objects",
      tags = {"User"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Order not found.")})
  @GetMapping(produces = "application/json")
  public @ResponseBody
  ResponseEntity<Page<User>> findUser(
      @And( {
                @Spec(path = "name", params = "name", spec = Like.class),
                @Spec(path = "surname", params = "nameCar", spec = Like.class),
                @Spec(path = "email", params = "email", spec = Like.class)})
          Specification<User> specification,
      Pageable pageable) {
    return responseHttpOk(userService.findUsers(specification,pageable));
  }

  /**
   *
   * @param userDto dto
   * @return User from UserDto
   */
  private User convertToEntity(UserDto userDto) {
    return modelMapper.map(userDto, User.class);
  }

}
