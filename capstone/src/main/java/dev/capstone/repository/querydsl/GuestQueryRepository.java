package dev.capstone.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.capstone.domain.enumerated.YesOrNo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static dev.capstone.domain.QGuest.*;

@Repository
public class GuestQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public GuestQueryRepository(EntityManager em, JPAQueryFactory query) {
        this.em = em;
        this.query = query;
    }

    public void inTable(String token, Integer table) {
        query.update(guest)
                .where(guest.token.eq(token))
                .set(Collections.singletonList(guest.waiting), Collections.singletonList(YesOrNo.valueOf("No")))
                .set(guest.table, table)
                .execute();
    }

    public void deleteAllByApproved() {
        query.delete(guest)
                .where(guest.approved.eq(YesOrNo.valueOf("Yes")))
                .execute();
    }

    public void updateApprovedByToken(String token, Integer table) {
        query.update(guest)
                .where(guest.table.eq(table))
                .set(Collections.singletonList(guest.approved), Collections.singletonList(YesOrNo.valueOf("Yes")))
                .execute();
    }

    public Integer findHighestNumberOfGuestNumber(Integer marketID) {
        List<Integer> getMaxNumberGuest = query.select(guest.guest_number.max())
                .from(guest)
                .fetch();
        if (getMaxNumberGuest.isEmpty())
            return 0;
        else
            return getMaxNumberGuest.get(0);
    }

}
