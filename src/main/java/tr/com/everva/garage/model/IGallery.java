package tr.com.everva.garage.model;

import tr.com.everva.garage.model.entity.Gallery;

public interface IGallery extends IEntity {

    Gallery getGallery();

    void setGallery(Gallery gallery);
}
