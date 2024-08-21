package com.example.contersback.Controller;


import com.example.contersback.DAO.PhotosRepository;
import com.example.contersback.DAO.UserRepository;
import com.example.contersback.model.Photo;
import com.example.contersback.model.PhotoStatus;
import com.example.contersback.model.RequestData;
import com.example.contersback.model.Operator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotosController {



    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotosRepository photosRepository;

    @GetMapping("/photos")
    public List<Photo> getPhotosForCheck(@RequestParam String login, @RequestParam String type) {
        Operator operator;
        if(userRepository.findByLogin(login) == null) {
            operator = new Operator(login);
            userRepository.save(operator);
        }

       operator = userRepository.findByLogin(login);

        long id = operator.getId();

        int page = 0;
        int size = 500; // Устанавливаем размер страницы в 500 записей
        List<Photo> result = new ArrayList<>();

        // Пока не найдены нужные записи и страницы не закончились
        while (result.size() < 10) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Photo> photoPage = photosRepository.findByUserIdAndTypeAndStatus(0L, type, PhotoStatus.UNCHECKED, pageable);
            List<Photo> photos = photoPage.getContent();

            for (Photo photo : photos) {
                photo.setUserId(id);
                photosRepository.save(photo);
                result.add(photo);
                if (result.size() == 10) {
                    break;
                }
            }

            // Если страницы закончились, прекращаем поиск
            if (!photoPage.hasNext()) {
                break;
            }

            // Переход на следующую страницу
            page++;
        }

        return result;
    }


    @PostMapping("/photos")
    public String updatePhotos(@RequestBody RequestData data) {
        List<Photo> photos = data.getPhotos();
        List<Photo> sentPhotos = data.getSentPhotos();
        for(Photo photo : sentPhotos) {
            if(!photos.contains(photo)) {
                photo.setUserId(0);
            }
            photosRepository.save(photo);
        }
        return "data updated";
    }

}
