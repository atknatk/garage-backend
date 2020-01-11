package tr.com.everva.garage.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.enums.ErrorCode;

@Getter
@Setter
@Builder
public class ResponseDto {

    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorCode errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

}
