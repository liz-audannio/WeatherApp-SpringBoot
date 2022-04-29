package com.tts.WeatherAPP;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long>{
	List<ZipCode> findAllByOrderByIdAsc(Pageable pagelimit);
}
