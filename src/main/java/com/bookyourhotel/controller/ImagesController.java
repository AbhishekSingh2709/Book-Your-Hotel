package com.bookyourhotel.controller;

import com.bookyourhotel.dto.ImagesDto;
import com.bookyourhotel.service.ImagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/Images")
public class ImagesController
{
    private ImagesService im;

    public ImagesController(ImagesService im) {
        this.im = im;
    }

    @PostMapping(path = "/uploadImages/file/{bucketName}/property/{propertyId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImagesDto> uploadPropertyImages(@RequestParam MultipartFile file,
                                                          @PathVariable String bucketName,
                                                          @PathVariable Long propertyId
                                                          //@AuthenticationPrincipal AppUserEntity user
    )
    {
        ImagesDto imagesDto = im.uploadImages(file, bucketName, propertyId);
        return new ResponseEntity<>(imagesDto , HttpStatus.CREATED);
    }
}
