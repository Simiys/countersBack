package com.example.contersback.DAO;

import com.example.contersback.model.Photo;
import com.example.contersback.model.PhotoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PhotosRepository extends JpaRepository<Photo, Long> {

    List<Photo> getTop10ByUserIdAndTypeAndStatus(Long userId, String type, PhotoStatus status);

    Page<Photo> findByUserIdAndTypeAndStatus(Long userId, String type, PhotoStatus status, Pageable pageable);

}
