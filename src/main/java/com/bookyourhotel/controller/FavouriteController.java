package com.bookyourhotel.controller;

import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.FavouriteEntity;
import com.bookyourhotel.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Favourites")
public class FavouriteController
{
    private FavouriteService fs;

    public FavouriteController(FavouriteService fs) {
        this.fs = fs;
    }

    @PostMapping("/addFavourite")
    public ResponseEntity<FavouriteEntity> addFavourites(@AuthenticationPrincipal AppUserEntity user, @RequestParam Long propertyId,
                                                         @RequestBody FavouriteEntity favourite)
    {
        FavouriteEntity favouriteEntity = fs.addFavourite(user, propertyId, favourite);
        return new ResponseEntity<>(favouriteEntity , HttpStatus.CREATED);
    }
}
