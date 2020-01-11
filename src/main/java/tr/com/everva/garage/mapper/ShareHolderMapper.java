package tr.com.everva.garage.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.entity.ShareHolder;

import java.util.List;

@Mapper
public interface ShareHolderMapper {

    ShareHolderMapper INSTANCE = Mappers.getMapper(ShareHolderMapper.class);

    ShareHolderDto toDto(ShareHolder dto);

    ShareHolder toEntity(ShareHolderDto dto);

    @IterableMapping
    List<ShareHolderDto> toDtoList(List<ShareHolder> holderList);

}
