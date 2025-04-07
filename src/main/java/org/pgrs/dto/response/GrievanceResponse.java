package org.pgrs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrievanceResponse {

    private Long grievanceId;
    private String userName;
    private String message;

}
