package com.bookyourhotel.service;

import com.bookyourhotel.dto.AppUserDto;
import com.bookyourhotel.dto.TokenDto;
import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService
{
    private AppUserRepository aur;
    private JwtService js;

    public AppUserService(AppUserRepository aur, JwtService js) {
        this.aur = aur;
        this.js = js;
    }


    public  ResponseEntity<?> addUser(AppUserDto dto)
    {
         if (aur.existsByUsername(dto.getUsername()))
         {
             return new ResponseEntity<String>("UserName already exists" , HttpStatus.OK);
         }
         if (aur.existsByEmailid(dto.getEmailid()))
         {
             return new ResponseEntity<String>("Email already exists" , HttpStatus.OK);
         }
        AppUserEntity appUserEntity = DtoToEntity(dto);
        AppUserEntity saved = aur.save(appUserEntity);

        AppUserDto appUserDto = EntityToDto(saved);
        return new ResponseEntity<AppUserDto>(appUserDto, HttpStatus.CREATED);

    }

    public AppUserEntity DtoToEntity(AppUserDto dto)
    {
        AppUserEntity entity = new AppUserEntity();
        entity.setName(dto.getName());
        entity.setEmailid(dto.getEmailid());
        entity.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        entity.setUsername(dto.getUsername());
        entity.setRole(dto.getRole());
        return entity;
    }

    public AppUserDto EntityToDto(AppUserEntity entity)
    {
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmailid(entity.getEmailid());
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole());
        return dto;
    }

    public TokenDto verifyUser(AppUserDto dto)
    {
        Optional<AppUserEntity> userEntity = aur.findByUsername(dto.getUsername());
        if (userEntity.isPresent())
        {
            AppUserEntity aue = userEntity.get();
            if (BCrypt.checkpw(dto.getPassword(), aue.getPassword()))
            {
                String token = js.generateToken(aue);
                TokenDto tokenDto = new TokenDto();
                tokenDto.setType("JWT");
                tokenDto.setToken(token);
                return tokenDto;
            }
        }
        return null;
    }

}
