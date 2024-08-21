package com.bookyourhotel.service;

import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.FavouriteEntity;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.repository.AppUserRepository;
import com.bookyourhotel.repository.FavouriteRepository;
import com.bookyourhotel.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavouriteService {

    private FavouriteRepository fr;
    private PropertyRepository pr;

    public FavouriteService(FavouriteRepository fr, AppUserRepository ar, PropertyRepository pr) {
        this.fr = fr;
        this.pr = pr;
    }

    public FavouriteEntity addFavourite (AppUserEntity user , String id , FavouriteEntity favourite)
    {
        PropertyEntity property = pr.findById(id).get();
        String favoriteId = UUID.randomUUID().toString();
        favourite.setId(favoriteId);
        favourite.setPropertyEntity(property);
        favourite.setAppUserEntity(user);
        return fr.save(favourite);
    }
}
