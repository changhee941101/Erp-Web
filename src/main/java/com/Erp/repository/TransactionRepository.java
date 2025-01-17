package com.Erp.repository;

import com.Erp.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {



    @Query( "SELECT count(t) FROM Transaction t WHERE " +
            "CAST(t.id AS string) = :nameParam OR " +
            "t.companyName LIKE %:nameParam% OR " +
            "cast(t.amount as string) = :nameParam OR " +
            "t.trDate LIKE %:nameParam% OR " +
            " CAST(t.quarter AS string) = :nameParam OR " +
            "t.remark LIKE %:nameParam% OR " +
            "t.transactionCategory LIKE %:nameParam% ")
    long countByName(@Param("nameParam") String nameParam);

    //

    @Query( "SELECT t FROM Transaction t WHERE  " +
            "t.companyName LIKE %:nameParam% OR " +
            "cast(t.amount as string) = :nameParam OR " +
            "t.trDate LIKE %:nameParam% OR " +
            " CAST(t.quarter AS string) = :nameParam OR " +
            "t.remark LIKE %:nameParam% OR " +
            "t.transactionCategory LIKE %:nameParam%")
    List<Transaction> findDataByName(@Param("nameParam") String nameParam, Pageable pageable);


    @Query("SELECT t FROM Transaction t WHERE CAST(t.id AS string) = :nameParam")
    List<Transaction> findcompanyNum(String nameParam, Pageable pageable);

//    @Query("SELECT t FROM Transaction t WHERE CAST(t.id AS string) = :nameParam")
//    List<Transaction> findcompanyNum(String nameParam, Pageable pageable);


    @Query("SELECT t FROM Transaction t ")
    List<Transaction> findAllData(Pageable pageable);

    @Query(value = " SELECT * FROM Transactions t WHERE YEAR(t.tr_Date) = :year AND t.quarter = :quarter ORDER BY t.tr_Date asc, t.quarter asc", nativeQuery = true)
    List<Transaction> findTransactionList(@Param("year") Integer year, @Param("quarter") Integer quarter);

    @Query("select t.trDate, t.amount from Transaction t order by t.trDate")
    List<Object[]> findTrDataList();

    @Query("select t.companyName,t.trDate,t.amount from Transaction t order by t.companyName ")
    List<Object[]> findSelectName();

    @Query("select t.trDate, t.amount ,t.companyName from Transaction t where t.companyName like :companyName order by t.trDate")
    List<Object[]> findDateAndAmount(@Param("companyName") String companyName);

    @Query("select t from Transaction t ")
    List<Transaction> findData(int start, int length);
}
