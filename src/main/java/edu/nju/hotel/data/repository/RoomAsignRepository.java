package edu.nju.hotel.data.repository;

import edu.nju.hotel.data.model.RoomAsign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by dzkan on 2016/3/8.
 */
@Repository
public interface RoomAsignRepository extends JpaRepository<RoomAsign, Integer> {
    @Query("select r.roomByRoomId.id from RoomAsign r where r.roomByRoomId.roomTypeByRoomTypeId.id=?1 and r.id not in (select r2.id from RoomAsign r2 where r2.inTime>=?3 or r2.outTime<=?2)")
    List<Integer> getAssignRoomBetween(int id, Date start, Date end);

}
