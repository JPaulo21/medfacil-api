package com.api.medfacil.web.docs;

import com.api.medfacil.domain.entities.User;
import com.api.medfacil.web.dto.user.UserDTO;
import com.api.medfacil.web.dto.user.UserQueryDTO;
import com.api.medfacil.web.exceptions.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Users", description = "Endpoints for users operations")
public interface UserDocs {

    @Operation(summary = "Create new user", description = "will receive user data to register", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Users",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Users",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))
            )
    })
    public ResponseEntity<Void> create(UserDTO userDTO, UriComponentsBuilder uc);

    @Operation(summary = "get User by CPF", description = "will receive user data to register", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Users",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Users",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "Users",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))
            )
    })
    public ResponseEntity<UserQueryDTO> getUserByCPF(String cpf);

}
