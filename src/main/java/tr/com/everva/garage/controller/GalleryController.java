package tr.com.everva.garage.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.gallery.GalleryCreateDto;
import tr.com.everva.garage.service.GalleryService;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("gallery")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody GalleryCreateDto dto) {
        ResponseDto verified = galleryService.create(dto);
        return ResponseEntity.ok(verified);
    }
}
