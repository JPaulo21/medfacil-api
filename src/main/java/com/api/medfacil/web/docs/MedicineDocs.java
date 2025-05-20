package com.api.medfacil.web.docs;

import com.api.medfacil.web.dto.medicine.MedicineDTO;
import com.api.medfacil.web.dto.medicine.MedicineQueryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface MedicineDocs {

    @Operation(summary = "Create a new medicine for user", description = "Create a medicine with data de ", tags = {"Medicines"})
    public ResponseEntity<Void> create(MedicineDTO medicineDTO, UriComponentsBuilder ucb);

    @Operation(summary = "Get medicines by user id", description = "will listed datas all medicines by user id", tags = {"Medicines"}
            , security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value =
        @ApiResponse(
                responseCode = "200", description = "Medicines",
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MedicineQueryDTO.class)))
        )
    )
    public ResponseEntity<Page<MedicineDTO>> getMedicinesByUser(Integer id, Pageable page);

}
