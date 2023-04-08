package com.noobs.gazonuz.repositories;

import com.noobs.gazonuz.domains.Pitch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PitchPaginationRepository extends PagingAndSortingRepository<Pitch, String> {
   int PER_PAGE = 10;
   @Query("select p from Pitch p where cast(p.latitude as double)>=:minLatitude and cast(p.latitude as double)<=:maxLatitude and cast(p.longitude as double)>=:minLongitude and cast(p.longitude as double)<=:maxLongitude and upper(p.name) like upper(concat('%', :searchBy, '%'))")
   List<Pitch> pitches(@Param("searchBy") String searchBy,@Param("minLatitude") Double minLatitude,@Param("maxLatitude") Double maxLatitude, @Param("minLongitude") Double minLongitude,@Param("maxLongitude") Double maxLongitude, Pageable pageable);
   @Query("select count(p) from Pitch p where  cast( p.latitude as double )>=:minLatitude and cast(p.latitude as double)<=:maxLatitude and cast(p.longitude as double)>=:minLongitude and cast(p.longitude as double)<=:maxLongitude and upper(p.name) like upper(concat('%', :searchBy, '%'))")
   long pitchesCount(@Param("searchBy") String searchBy,@Param("minLatitude") Double minLatitude,@Param("maxLatitude") Double maxLatitude, @Param("minLongitude") Double minLongitude,@Param("maxLongitude") Double maxLongitude);



//   @Query("select count(p)  from Pitch p where  p.latitude>=:minLatitude and p.latitude<=:maxLatitude and p.longitude>=:minLongitude and p.longitude<=:maxLongitude")
//   Long pitchesCount(@Param("minLatitude") Double minLatitude,@Param("maxLatitude") Double maxLatitude, @Param("minLongitude") Double minLongitude,@Param("maxLongitude") Double maxLongitude, Pageable pageable);


}
