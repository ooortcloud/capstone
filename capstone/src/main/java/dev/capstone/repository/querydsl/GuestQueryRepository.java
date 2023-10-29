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

    public void inTable(String token, Integer tableNum) {
        query.update(guest)
                .where(guest.token.eq(token))
                .set(Collections.singletonList(guest.waiting), Collections.singletonList(YesOrNo.valueOf("No")))
                .set(guest.tableNum, tableNum)
                .execute();

        em.clear();
        em.flush();
    }

    public void deleteAllByApproved() {
        query.delete(guest)
                .where(guest.approved.eq(YesOrNo.valueOf("Yes")))
                .execute();
    }

    public void updateApprovedByGnumber(Integer gnumber) {
        query.update(guest)
                .where(guest.gnumber.eq(gnumber))
                .set(Collections.singletonList(guest.approved), Collections.singletonList(YesOrNo.valueOf("Yes")))
                .execute();

        em.clear();
        em.flush();
    }

    public Integer findHighestNumberOfGnumber(Integer marketID) {
        List<Integer> getMaxGnumber = query.
                select(guest.gnumber.max())
                .from(guest)
                .fetch();
        if (getMaxGnumber.get(0) == null)
            return 0;
        else
            return getMaxGnumber.get(0);
    }

}
