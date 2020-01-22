package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.util.ContextUtils;
import tr.com.everva.garage.util.GalleryContext;

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
public class Vehicle {

    @Id
    @GeneratedValue(generator = "vehicle_dep_id")
    @GenericGenerator(
            name = "vehicle_dep_id",
            strategy = "tr.com.everva.garage.util.VehicleIdGenerator"
            //,parameters = {@Parameter(name = IdGenerator.INCREMENT_PARAM, value = "50")}
    )
    @Column(name = "id", unique = true)
    private int id;

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

    @ManyToOne
    @JoinColumn(name = "gallery_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Gallery gallery;

    @Column(name = "gallery_id")
    protected String galleryId;

    @Column(name = "created_at")
    protected Date createdAt;

    @Column(name = "updated_at")
    protected Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_user_id", referencedColumnName = "id")
    private User updatedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        createdBy = ContextUtils.getCurrentUserForRepository();
        String currentGallery = GalleryContext.getCurrentGallery();
        Gallery gallery = new Gallery();
        gallery.setId(currentGallery);
        setGallery(gallery);
        setGalleryId(currentGallery);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        updatedBy = ContextUtils.getCurrentUserForRepository();
    }

    public Vehicle(int id) {
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
