package com.jea.cashpals.mapper;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.dto.TransactionDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "id", ignore = true)
    Transaction fromTransactionDTO(TransactionDTO source);

    TransactionDTO fromTransaction(Transaction source);
}