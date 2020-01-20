package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.IGallery;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.base.BaseGalleryAuditUserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@FilterDef(name = "galleryFilter", parameters = {@ParamDef(name = "galleryId", type = "string")})
//@Filter(name = "galleryFilter", condition = "gallery_id = :galleryId")
public class Vehicle extends BaseGalleryAuditUserEntity implements IGallery, IAuditEntity {

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_vehicle_category_id"))
    private Category category;

    private String year;

    private String color;

    private int km;

    private String sahibindenId;

    @Column(columnDefinition = "TEXT")
    private String note;

    private Date soldAt;

    private Date boughtAt;

    private boolean sold;

    @Column(precision = 12, scale = 2)
    private double buyingPrice;

    @Column(precision = 12, scale = 2)
    private double soldPrice;

    @OneToMany(mappedBy = "vehicle")
    private List<Expense> expenses;

    public Vehicle(String id) {
        setId(id);
    }

    public Vehicle(VehicleCreateDto dto) {
        if (!StringUtils.isEmpty(dto.getCategory())) {
            setCategory(new Category(dto.getCategory()));
        }
        setYear(dto.getYear());
        setColor(dto.getColor());
        setKm(dto.getKm());
        setNote(dto.getNote());
        setBoughtAt(dto.getBoughtAt());
        setBuyingPrice(dto.getBuyingPrice());
        setSold(false);
    }


    public void merge(VehicleUpdateDto vehicleUpdateDto) {
        this.setYear(vehicleUpdateDto.getYear());
        this.setColor(vehicleUpdateDto.getColor());
        this.setKm(vehicleUpdateDto.getKm());
        this.setNote(vehicleUpdateDto.getNote());
        this.setBuyingPrice(vehicleUpdateDto.getBuyingPrice());
    }


}
