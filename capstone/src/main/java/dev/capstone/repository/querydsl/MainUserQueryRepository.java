package dev.capstone.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.capstone.dto.ChangeMainUserDTO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static dev.capstone.domain.QMainUser.mainUser;

@Repository
public class MainUserQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public MainUserQueryRepository(EntityManager em, JPAQueryFactory query) {
        this.em = em;
        this.query = query;
    }

    public void updateMainUser(ChangeMainUserDTO dto) {
        query.update(mainUser)
                .set(mainUser.userpw, dto.getUser_pw())
                .set(mainUser.username, dto.getUser_name())
                .set(mainUser.userresidence, dto.getUser_residence())
                .where(mainUser.userid.eq(dto.getUser_id()))
                .execute();

        em.flush();
        em.clear();
    }
}
