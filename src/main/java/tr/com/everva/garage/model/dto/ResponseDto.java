package tr.com.everva.garage.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {

    private boolean success;

    private Object data;

    private String errorCode;

    private String error;

}
