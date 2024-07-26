package com.bookyourhotel.service;

import com.bookyourhotel.dto.BookingDto;
import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.BookingEntity;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.repository.BookingRepository;
import com.bookyourhotel.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookingService
{
    private BookingRepository br;
    private PropertyRepository pr;
    private PdfService ps;
    private BucketService bs;
    private TwilioService ts;

    public BookingService(BookingRepository br, PropertyRepository pr, PdfService ps, BucketService bs, TwilioService ts) {
        this.br = br;
        this.pr = pr;
        this.ps = ps;
        this.bs = bs;
        this.ts = ts;
    }

    public BookingDto bookProperty(long id, AppUserEntity user , BookingDto booking)
    {
        PropertyEntity property = pr.findById(id).get();
        Integer pricePerNight = property.getPrice();
        int totalPrice = (pricePerNight)*(booking.getTotalNights());

        BookingEntity bookingEntity = DtoToEntity(booking);
        bookingEntity.setPrice(totalPrice);
        bookingEntity.setPropertyEntity(property);
        bookingEntity.setAppUserEntity(user);
        BookingEntity saved = br.save(bookingEntity);

        BookingDto bookingDto = EntityToDto(saved);
        boolean b = ps.generatePdf(bookingDto.getId() + "-Confirmation", bookingDto);

        if (b)
        {
            try {
                MultipartFile file = bs.convert("G://PSA//Intellij//WorkingDirectory//Pdf//"+bookingDto.getId()+"-Confirmation"+".pdf");
                String uploadedFileUrl = bs.uploadFile(file, "abhishek-psa-project");
                String smsId = ts.sendSms(bookingDto.getMobile(), "Your hotel has been booked . Click for more information" + uploadedFileUrl);
                System.out.println(smsId);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bookingDto;
    }

    public BookingEntity DtoToEntity (BookingDto booking)
    {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setName(booking.getName());
        bookingEntity.setEmailId(booking.getEmailId());
        bookingEntity.setMobile(booking.getMobile());
        bookingEntity.setTotalNights(booking.getTotalNights());
        return bookingEntity;
    }

    public BookingDto EntityToDto(BookingEntity entity)
    {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(entity.getId());
        bookingDto.setName(entity.getName());
        bookingDto.setEmailId(entity.getEmailId());
        bookingDto.setMobile(entity.getMobile());
        bookingDto.setPrice(entity.getPrice());
        bookingDto.setTotalNights(entity.getTotalNights());
        bookingDto.setEntity(entity.getPropertyEntity());
        bookingDto.setUser(entity.getAppUserEntity());
        return bookingDto;
    }
}
