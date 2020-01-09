package tr.com.everva.garage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.Vehicle;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle fromCreateDtoToEntity(VehicleCreateDto dto);

    Vehicle fromUpdateDtoToEntity(VehicleUpdateDto dto);


}
