package com.parkmate.userservice.userfavorite.infrastructure;

import com.parkmate.userservice.common.response.CursorPage;
import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteGetRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFavoriteCustomRepositoryImpl implements UserFavoriteCustomRepository {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CursorPage<UserFavorite> getParkingLotUuidsByUserUuidAndIsDeletedFalse(UserFavoriteGetRequestDto userFavoriteGetRequestDto) {

        int pageSize = userFavoriteGetRequestDto.getSize() != null ? userFavoriteGetRequestDto.getSize() : DEFAULT_PAGE_SIZE;
        String cursor = userFavoriteGetRequestDto.getCursor();
        String userUuid = userFavoriteGetRequestDto.getUserUuid();

        StringBuilder sql = new StringBuilder("SELECT uf FROM UserFavorite uf WHERE uf.userUuid = :userUuid And uf.isDeleted = false");

        if (cursor != null) {
            sql.append(" AND uf.id < :cursor");
        }

        sql.append(" ORDER BY uf.id DESC");

        TypedQuery<UserFavorite> query = entityManager.createQuery(sql.toString(), UserFavorite.class)
                .setParameter("userUuid", userUuid)
                .setMaxResults(pageSize);

        if (cursor != null) {
            query.setParameter("cursor", Long.parseLong(cursor));
        }

        List<UserFavorite> results = query.getResultList();

        String nextCursor = results.size() == pageSize
                ? String.valueOf(results.get(results.size() - 1).getId())
                : null;

        boolean hasNext = results.size() == pageSize;

        return CursorPage.of(results, hasNext, nextCursor);
    }

}
